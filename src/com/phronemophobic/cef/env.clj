(ns com.phronemophobic.cef.env
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.java.shell :as sh]
            
            [com.phronemophobic.fs :as fs])
  (:import com.sun.jna.Platform
           java.net.URLEncoder
           java.nio.file.Files
           java.nio.file.attribute.FileAttribute))



(def cef-archs #{"arm64" "64" "32" "arm"} )
(def cef-platforms #{"windows" "linux" "macos"})

(defn guess-platform
  ([]
   (guess-platform (System/getProperty "os.name")))
  ([os-name]
   (cond
     (= os-name "Mac OS X")
     "macos"

     (str/starts-with? os-name "Windows")
     "windows"

     (str/starts-with? os-name "Linux")
     "linux"

     :else :unknown)))

(def cef-platform (delay (guess-platform)))

(defn guess-arch
  ([]
   (guess-arch (System/getProperty "os.arch")))
  ([os-arch]
   (case os-arch
     ("x86_64" "amd64")
     (if (= "macos" @cef-platform)
       "x64"
       "64")

     ("arm64" "aarch64")
     "arm64"

     ("x86" "i386" "i486" "i586" "i686")
     "32"

     ;; ????
     ;; "arm"

     :unknown)))

(def cef-arch (delay (guess-arch)))



;; 117.2.4+g5053a95+chromium-117.0.5938.150
(def cef-version {:cef "117.2.4+g5053a95"
                  :chromium "117.0.5938.150"
                  :build "minimal"
                  ;; :build "standard"
                  })

(def cef-build (delay
                 (merge
                  cef-version
                  {:arch @cef-arch
                   :platform @cef-platform})))


(defn download-fname [{:keys [cef chromium arch platform build]}]
  (let [fname (format
               ;;"cef_binary_%s+chromium-%s_%s%s_minimal.tar.bz2"
               "cef_binary_%s+chromium-%s_%s%s%s.tar.bz2"
               cef chromium platform arch
               (if (= build "standard")
                 ""
                 (str "_" build)))]
    fname))

(defn unzipped-fname [{:keys [cef chromium arch platform] :as build}]
  (let [s (download-fname build)]
    (subs s 0 (- (count s) (count ".tar.bz2")))))


(defn download-url [{:keys [cef chromium arch platform]
                     :as build}]
  (let [
        base "https://cef-builds.spotifycdn.com/"
        url-str (format "%s%s" base (URLEncoder/encode (download-fname build) "utf-8"))]
    (clojure.java.io/as-url url-str)))

(def default-target-dir (io/file "/tmp" "com.phronemophobic.cef"))
(defn extract-helper
  "Extract the helper process executable from the jar to the file system.

  To support sandboxing, the cef requries a separate executable that can be called. The executable must exist on the file system to be run."
  ([]
   (extract-helper (doto default-target-dir
                     (.mkdir))))
  ([target-dir]
   (let [resource-base (if (= @cef-arch "arm64")
                         "darwin-aarch64"
                         "darwin-x86-64")
         source (io/resource (str resource-base "/ceflib Helper"))
         target-path (io/file target-dir "ceflib Helper")]
     (when-not (.exists target-path)
       (with-open [in (io/input-stream source)
                  out (io/output-stream target-path)]
         (io/copy in out))
       (.setExecutable target-path true true)
       (.setExecutable target-path true false)))
   nil))

(defn download-and-extract-framework-linux
  "The Chromium Framework is about 234M (500M on linux) unzipped which doesn't belong in the clojure jar. Download and extract the framework to target-dir."
  ([target-dir build]
   (.mkdir target-dir)
   (let [
         url (download-url build)

         target-dir (.getAbsoluteFile target-dir)
         target-download (io/file target-dir "cef.tar.bz2")

         cef-dir (io/file target-dir (unzipped-fname build))

         last-copied-file (io/file target-dir "libcljcef.so")
         ]
     (when-not (.exists last-copied-file)
       (when-not (.exists target-download)
         ;; (println "downloading")
         (with-open [url-stream (io/input-stream url)]
           (io/copy url-stream target-download)))
       
       ;; (println "extracting...")
       (when-not (.exists cef-dir)
         (fs/untar-bz2 target-download target-dir))

       (doseq [folder ["Resources" "Release"]]
         (doseq [f (.listFiles (io/file cef-dir folder))]
           (try
             (Files/createSymbolicLink (.toPath (io/file target-dir (.getName f)))
                                       (.toPath f)
                                       (into-array FileAttribute []))
             (catch java.nio.file.FileAlreadyExistsException e
               nil))))

       (doseq [resource ["ceflib Helper"
                         "libcljcef.so"]]
         (with-open [is (io/input-stream (io/resource (str "extract/linux-x86-64/" resource)))]
           (io/copy is
                    (io/file target-dir resource)))
         (.setExecutable (io/file target-dir resource) true true))

       (when (.exists target-download)
         (.delete target-download)))

     nil)))

(defn download-and-extract-framework-macosx
  "The Chromium Framework is about 234M unzipped which doesn't belong in the clojure jar. Download and extract the framework to target-dir."
  ([target-dir build]
   (.mkdir target-dir)
   (let [url (download-url build)
         target-dir (.getAbsoluteFile target-dir)
         target-download (io/file target-dir "cef.tar.bz2")
         framework-path (io/file target-dir
                                 (unzipped-fname build)
                                 ;;"Debug"
                                 "Release"
                                 "Chromium Embedded Framework.framework")
         link-path (io/file target-dir "Chromium Embedded Framework.framework")]
     (when-not (.exists link-path)
       (when-not (.exists target-download)
         (println "downloading" url)
         (with-open [url-stream (io/input-stream url)]
           (io/copy url-stream target-download)))

       (println "extracting...")
       (when-not (.exists framework-path)
         (fs/untar-bz2 target-download target-dir))

       ;; Files.createSymbolicLink(newLink, target);
       (when-not (.exists link-path)
         (Files/createSymbolicLink (.toPath link-path)
                                   (.toPath framework-path)
                                   (into-array FileAttribute [])))


       ;; extract libraries
       (doseq [f (-> (io/file framework-path
                              "Libraries")
                     (.listFiles))
               :when (str/ends-with? (.getName f) ".dylib")]
         (with-open [os (io/output-stream (io/file target-dir
                                                   (.getName f)))]
           (io/copy f os))))

     (when (.exists target-download)
       (.delete target-download))

     nil)))

(defn download-and-extract-framework
  "The Chromium Framework is about 234M (500M on linux) unzipped which doesn't belong in the clojure jar. Download and extract the framework to target-dir."
  ([]
   (download-and-extract-framework default-target-dir
                                   @cef-build))
  ([target-dir]
   (download-and-extract-framework target-dir
                                   @cef-build))
  ([target-dir build]
   (if (Platform/isLinux)
     (download-and-extract-framework-linux target-dir build)
     (download-and-extract-framework-macosx target-dir build))))

(defn compile-ceflib
  ([]
   (compile-ceflib {}))
  ([{:keys [arch]}]
   (let [build @cef-build
         build (if arch
                 (assoc build :arch (guess-arch (str arch)))
                 build)
         target-dir default-target-dir]
     (download-and-extract-framework target-dir build)
     (let [cef-dir-path (.getCanonicalPath (io/file target-dir (unzipped-fname build)))
           script-path (.getCanonicalPath (io/file "csource"
                                                   (case @cef-platform
                                                     "macos" "compile_macosx.sh"
                                                     "linux" "compile_linux.sh")))

           arch (if (#{"x86_64" "x64" "64"} (:arch build))
                  "x86_64"
                  "arm64")
           cmd [script-path arch cef-dir-path]
           _ (prn cmd)
           result (apply sh/sh cmd)]
       (println "exit code:" (:exit result))
       (println (:out result))
       (println (:err result))))))
