(ns com.phronemophobic.gen3
  (:require [com.phronemophobic.clong.gen.jna :as gen]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]
            [com.rpl.specter :as specter]
            [clojure.string :as str]
            [com.phronemophobic.cinterop :as cinterop]
            )
  (:import java.lang.ref.Cleaner
           com.sun.jna.Memory
           java.nio.charset.CodingErrorAction
           java.nio.charset.CharsetDecoder
           java.nio.charset.Charset
           java.nio.ByteBuffer
           java.nio.CharBuffer
           com.sun.jna.Platform
           com.sun.jna.Memory
           com.sun.jna.Function
           com.sun.jna.Pointer
           com.sun.jna.CallbackReference
           com.sun.jna.ptr.IntByReference
           com.sun.jna.ptr.ShortByReference
           com.sun.jna.ptr.FloatByReference
           com.sun.jna.Structure
           com.sun.jna.WString
           java.util.concurrent.ConcurrentHashMap
           java.util.concurrent.atomic.AtomicInteger)
  (:gen-class))

;; Reference Counting Reference
;; https://bitbucket.org/chromiumembedded/cef/wiki/UsingTheCAPI.md
;; 1. Do not increment/decrement the reference count when passing a structure to its own member function:
;; 2. Increment the reference count on a struct before passing it as an argument to another struct:
;; 3. Decrement the reference count on a struct when receiving it as an argument from somewhere else after you're done using it:
;; 4. Add a reference to a handler before passing it into, for instance, cef_create_browser(). The API will remove a reference from the handler when the handler is no longer needed.
;; 5. Use an atomic reference counting implementation because add_ref and release may be called from multiple threads. The WinAPI InterlockedIncrement() and InterlockedDecrement() functions can be used for this purpose.
;; 6. The handler should delete itself when the reference count reaches zero in the release() callback function that you've assigned for your structure.
;; 7. Reverse any additional references that your code adds to a struct (for instance, if you keep a reference to the cef_browser_t pointer in your handler implementation). The last opportunity to release references is in cef_handler_t::handle_before_window_closed().
;; 8. You'll know you're handling the reference counting correctly when nothing crashes and you no longer hit the DebugObjCt asserts on shutdown.

(def struct-prefix (gen/ns-struct-prefix *ns*))

(defn keywordize [s]
  (-> s
      (clojure.string/replace #"_" "-")
      keyword))

(defn symbolize [s]
  (-> s
      (clojure.string/replace #"_" "-")
      symbol))

(defn str->camelcase [s]
  (let [s (clojure.string/replace s #"_t$" "")
        parts (clojure.string/split s #"_" )
        parts (map clojure.string/capitalize parts)]
    (clojure.string/join parts)))

(defonce not-garbage (atom #{}))
(defn preserve! [o]
  (swap! not-garbage conj o)
  o)

(def cleaner (Cleaner/create))
(def ^:no-doc libc
  (com.sun.jna.NativeLibrary/getInstance "c"))


#_(let [malloc* (.getFunction libc "malloc")]
  (defn ^:private malloc [size]
    (.invoke ^Function malloc* Pointer
                     (to-array [(int size)]))))

#_(let [free* (.getFunction libc "free")]
  (defn ^:private free [p]
    (.invoke ^Function free* Void/TYPE
             (to-array [p]))))

(defn ^:private write-edn [w obj]
  (binding [*print-length* nil
            *print-level* nil
            *print-dup* false
            *print-meta* false
            *print-readably* true

            ;; namespaced maps not part of edn spec
            *print-namespace-maps* false

            *out* w]
    (pr obj)))

(defn ^:private parse-api []
  (let [
        base (io/file
              ".cef"
              "cef_binary_117.2.4+g5053a95+chromium-117.0.5938.150_macosarm64_minimal")
        include-dir (io/file base "include")
        cef-header-path (-> (io/file include-dir
                                     "capi"
                                     "cef_app_capi.h")
                            (.getCanonicalPath))

        default-arguments
        @(requiring-resolve 'com.phronemophobic.clong.clang/default-arguments)
        args (into default-arguments
                   [(str "-I" (.getCanonicalPath base))])]
    ((requiring-resolve 'com.phronemophobic.clong.clang/easy-api)
     cef-header-path
     args)))

(comment
  (def api (parse-api)))

(defn ^:private dump-api []
  (let [outf (io/file
              "resources"
              "com"
              "phronemophobic"
              "clj-cef"
              "api.edn")]
    (.mkdirs (.getParentFile outf))
    (with-open [w (io/writer outf)]
      (write-edn w (parse-api)))))


(def raw-api
  (with-open [rdr (io/reader
                     (io/resource
                      "com/phronemophobic/clj-cef/api.edn"))
                rdr (java.io.PushbackReader. rdr)]
      (edn/read rdr)))

(let [struct-prefix (gen/ns-struct-prefix *ns*)]
  (defmacro import-structs! []
    `(gen/import-structs! api ~struct-prefix)))

;; API mods
;; - all structs start with an underscore, but shouldn't
;; - structs have lots of good comments. can we fish those out?
;; - window-info is platform dependent. not sure if we need to make any changes.
;; - handle reference counting

(def under-prefixed-names
  [gen/ALL-TYPES
   gen/TYPE-TREE
   (fn [t]
      (and (keyword? t)
           (= "clong" (namespace t))
           (str/starts-with? (name t)
                             "_")))])

(defn fix-struct-names [api]
  (specter/transform
   (specter/multi-path
    [(specter/keypath :structs) specter/ALL (specter/keypath :id)]
    under-prefixed-names)
   (fn [t]
     (keyword "clong"
              (subs (name t) 1)))
   api))

(defn fix-forward-references [api]
  (specter/setval
   [(specter/keypath :structs)
    specter/ALL
    :fields
    specter/ALL
    :datatype
    gen/TYPE-TREE
    (fn [t]
      (and (vector? t)
           (= :coffi.mem/pointer (first t))
           (contains? #{:clong/cef_urlrequest_client_t
                        :clong/cef_urlrequest_t
                        :clong/cef_request_context_handler_t
                        :clong/cef_string_map_t
                        :clong/cef_string_multimap_t
                        :clong/cef_string_list_t}
                      (second t))))]
   :coffi.mem/pointer
   api))

(defn fix-linux-window-info* [api]
  (specter/setval
   [:structs
    specter/ALL
    #(= :clong/cef_window_info_t
        (:id %))]
   {:kind "CXCursor_StructDecl",
    :spelling "struct _cef_window_info_t",
    :type "CXType_Record",
    :id :clong/cef_window_info_t,
    :size-in-bytes 72,
    :fields
    [{:type "struct _cef_string_utf16_t",
      :datatype :clong/cef_string_utf16_t,
      :name "window_name",
      :bitfield? false,
      :calculated-offset 0}
     {:type "struct _cef_rect_t",
      :datatype :clong/cef_rect_t,
      :name "bounds",
      :bitfield? false,
      :calculated-offset 192}
     {:type "unsigned long",
      :datatype :coffi.mem/long,
      :name "parent_window",
      :bitfield? false,
      :calculated-offset 320}
     {:type "int",
      :datatype :coffi.mem/int,
      :name "windowless_rendering_enabled",
      :bitfield? false,
      :calculated-offset 384}
     {:type "int",
      :datatype :coffi.mem/int,
      :name "shared_texture_enabled",
      :bitfield? false,
      :calculated-offset 416}
     {:type "int",
      :datatype :coffi.mem/int,
      :name "external_begin_frame_enabled",
      :bitfield? false,
      :calculated-offset 448}
     {:type "unsigned long",
      :datatype :coffi.mem/long,
      :name "window",
      :bitfield? false,
      :calculated-offset 512}]}
   api))

(defn fix-linux-window-info [api]
  (if (Platform/isLinux)
    (fix-linux-window-info* api)
    api))

(def api (-> raw-api
             (fix-struct-names)
             (fix-forward-references)
             (fix-linux-window-info)
             (gen/replace-forward-references)))

(gen/def-enums (:enums api))
(gen/def-structs (:structs api) struct-prefix)


(defn fdef->defn [f]
  (let [{:keys [->fn name doc-string args]} (gen/fn-ast struct-prefix f)]
    `(let [f# (delay (~->fn @cinterop/cef))]
                (defn ~name ~doc-string ~args
                  (@f# ~@args)))))
;; (gen/def-api @cinterop/cef api)
(defmacro gen-fns []
  `(do
     ~@(map fdef->defn (:functions api))))

(gen-fns)

(import-structs!)

(defn cef-string [^String s]
  (if (= s "")
    (cef_string_utf16_t.)
    (let [bs (.getBytes s "utf-16le")
          len (alength bs)
          data (doto (Memory. len)
                 (.write 0 bs 0 len)
                 preserve!)
          _ (assert (even? len))
          data-len (quot len 2)
        
        
          cef-str (doto (cef_string_utf16_t.)
                    (.writeField "str" (doto (ShortByReference.)
                                         (.setPointer data)))
                    (.writeField "length" data-len))]
      cef-str)))

(defn cef-string-ref [^String s]
  (if (= s "")
    (cef_string_utf16_t.)
    (let [bs (.getBytes s "utf-16le")
          len (alength bs)
          data (doto (Memory. len)
                 (.write 0 bs 0 len)
                 preserve!)
          _ (assert (even? len))
          data-len (quot len 2)
          cef-str (doto (cef_string_utf16_tByReference.)
                    (.writeField "str" (doto (ShortByReference.)
                                         (.setPointer data)))
                    (.writeField "length" data-len))]
      cef-str)))

(defn refop! [s k]
  (.invoke (Function/getFunction
            (-> s :base k))
           Void/TYPE
           (to-array [(.getPointer s)])))
(defn add-ref! [s]
  (refop! s :add_ref))
(defn release! [s]
  (refop! s :release))
(defn has-one-ref! [s]
  (refop! s :has_one_ref))
(defn has-at-least-one-ref! [s]
  (refop! s :has_at_least_one_ref))


(defonce refcounts (ConcurrentHashMap.))
(let [absent
      (reify
        java.util.function.Function
        (apply [this arg]
          (AtomicInteger.)))]
 (defn ^:private add-ref!* [^Structure s]
   #_(let [address (-> s .getPointer Pointer/nativeValue)]
     (-> refcounts
         (.computeIfAbsent address absent)
         (.incrementAndGet)))))
(defn ^:private release!* [^Structure s]
  #_(let [address (-> s .getPointer Pointer/nativeValue)
        updated-count (-> refcounts
                          (.get address)
                          (.decrementAndGet))]
    (if (<= updated-count 0)
      (do
        (assert (zero? updated-count) "Negative Ref count!")
        (.remove refcounts address)
        ;; (free address)
        (int 1))
      (int 0))))
(defn ^:private has-one-ref!* [^Structure s]
  (int 0)
  #_(let [address (-> s .getPointer Pointer/nativeValue)
          updated-count (-> refcounts
                            (.get address)
                            (.get))]
      (if (= 1 updated-count)
        (int 1)
        (int 0))))
(defn ^:private has-at-least-one-ref!* [^Structure s]
  (int 1)
  #_(let [address (-> s .getPointer Pointer/nativeValue)
        updated-count (-> refcounts
                          (.get address)
                          (.get))]
    (if (>= updated-count 1)
      (int 1)
      (int 0))))


(defn cef_string_utf16->string [s]
  (let [len (:length s)]
    (if (pos? len)
      (let [^ShortByReference str (:str s)
            ^Pointer data (.getPointer str)]
        (String. (.getByteArray data 0 (* 2 len))
                 "utf-16le"))
      "")))

(defn uncoerce
  "Returns code for coercing a native type to clj type."
  [type vsym]
  (if (= [:coffi.mem/pointer :clong/cef_string_utf16_t] type)
    `(cef_string_utf16->string ~vsym)

    ;; else
    vsym))

(def main-class-loader @clojure.lang.Compiler/LOADER)

(defn coercer [type vsym]
  (if (vector? type)
    (case (first type)
      :coffi.ffi/fn
      (let [[_ arg-types ret-type] type
            args (map (fn [i]
                        (gensym (str "arg-" i "-")))
                      (range (count arg-types)))
            result## (gensym "result-")
            interface (gen/make-callback-interface-memo struct-prefix ret-type arg-types)]
       `(when ~vsym
          (let [ret-type# (gen/coffi-type->jna struct-prefix ~ret-type)
                arg-types# (into-array Class
                                       (into []
                                             (map #(gen/coffi-type->jna struct-prefix %))
                                             ~(vec arg-types)))
                cb#
                (preserve!
                 (reify
                   ~(symbol (.getName interface))
                   (~'callback [this# ~@args]
                    (.setContextClassLoader (Thread/currentThread) main-class-loader)
                    (~vsym ~@args))))
                
                cb-ptr# (preserve! (CallbackReference/getFunctionPointer cb#))]
            cb-ptr#)))

      :coffi.mem/pointer
      (case (second type)
        :clong/cef_string_utf16_t `(cef-string-ref ~vsym)
        ;; else
        vsym)

      ;;else
      vsym)

    (case type
      :clong/cef_string_utf16_t `(cef-string ~vsym)
      :coffi.mem/double `(double ~vsym)
      :coffi.mem/float `(float ~vsym)
      :coffi.mem/long `(long ~vsym)
      :coffi.mem/int `(int ~vsym)
      :coffi.mem/short `(short ~vsym)
      ;; else
      vsym)))

(def add-ref-cb
  (reify
    com.sun.jna.CallbackProxy
    (getParameterTypes [_]
      (into-array Class [Pointer]))
    (getReturnType [_]
      Void/TYPE)
    (callback [this args]
      (.setContextClassLoader (Thread/currentThread) main-class-loader)
      (add-ref!*
       (Structure/newInstance cef_base_ref_counted_tByReference (aget args 0))))))
(def add-ref-fp (CallbackReference/getFunctionPointer add-ref-cb))
(def release-cb
  (reify
    com.sun.jna.CallbackProxy
    (getParameterTypes [_]
      (into-array Class [Pointer]))
    (getReturnType [_]
      Integer/TYPE)
    (callback [this args]
      (.setContextClassLoader (Thread/currentThread) main-class-loader)
      (release!*
       (Structure/newInstance cef_base_ref_counted_tByReference (aget args 0))))))
(def release-fp (CallbackReference/getFunctionPointer release-cb))
(def has-one-ref-cb
  (reify
    com.sun.jna.CallbackProxy
    (getParameterTypes [_]
      (into-array Class [Pointer]))
    (getReturnType [_]
      Integer/TYPE)
    (callback [this args]
      (.setContextClassLoader (Thread/currentThread) main-class-loader)
      (has-one-ref!*
       (Structure/newInstance cef_base_ref_counted_tByReference (aget args 0))))))
(def has-one-ref-fp (CallbackReference/getFunctionPointer has-one-ref-cb))
(def has-at-least-one-ref-cb
  (reify
    com.sun.jna.CallbackProxy
    (getParameterTypes [_]
      (into-array Class [Pointer]))
    (getReturnType [_]
      Integer/TYPE)
    (callback [this args]
      (.setContextClassLoader (Thread/currentThread) main-class-loader)
      (has-at-least-one-ref!*
       (Structure/newInstance cef_base_ref_counted_tByReference (aget args 0))))))
(def has-at-least-one-ref-fp (CallbackReference/getFunctionPointer has-at-least-one-ref-cb))

(defn init-base! [struct]
  (let [^cef_base_ref_counted_t base (:base struct)]
    (doto base
      (.writeField "size" (long (.size struct)))
      (.writeField "add_ref" add-ref-fp)
      (.writeField "release" release-fp)
      (.writeField "has_one_ref" has-one-ref-fp)
      (.writeField "has_at_least_one_ref" has-at-least-one-ref-fp))
    (add-ref!* base)
    struct))

(defn gen-wrapper* [struct]
  (let [sname (name (:id struct))

        fname (symbol (str "map->"
                           (-> sname
                               (clojure.string/replace #"^cef_" "")
                               (clojure.string/replace #"_t$" "")
                               symbolize)))
        merge-fname (symbol (str "merge->"
                                 (-> sname
                                     (clojure.string/replace #"^cef_" "")
                                     (clojure.string/replace #"_t$" "")
                                     symbolize)))


        doc ""

        map-doc
        (str "Make a " sname "\n"
             doc)

        merge-doc
        (str "Merge properties of a " sname "\n"
             doc)

        struct-sym (with-meta 'struct
                     {:type Structure})
        
        constructor
        `(let [~struct-sym
               (preserve!
                (~(symbol 
                   (str struct-prefix "." sname "ByReference."))))]
           ~(when (= :clong/cef_base_ref_counted_t
                     (-> struct :fields first :datatype))
              `(init-base! ~struct-sym))
           ~(when (#{:clong/cef_settings_t
                     :clong/request_context_settings_t
                     :clong/cef_browser_settings_t} (:id struct))
              `(.writeField ~struct-sym "size" (long (.size ~struct-sym))))
           ~struct-sym)

        arg (into {:as 'm
                   :keys (vec
                          (for [field (:fields struct)]
                            (symbolize (:name field))))})


        
        set-property-fn `(fn [~struct-sym k# ~'vsym]
                           (case k#
                             ~@(into []
                                     (mapcat
                                      (fn [field]
                                        (let [test-constant (into
                                                             ()
                                                             (distinct)
                                                             [(:name field)
                                                              (keywordize (:name field))
                                                              (keyword (:name field))])]
                                          [test-constant
                                           `(.writeField ~struct-sym
                                                         ~(:name field)
                                                         ~(coercer (:datatype field) 'vsym))])))
                                     (:fields struct))))
        set-property-fn## (gensym "set-property-")

        set-properties
        `(doseq [[k# ~'vsym] ~'m]
           (~set-property-fn## ~struct-sym k# ~'vsym))]

    `(do
       (let [~set-property-fn## ~set-property-fn]
         (defn ~merge-fname ~merge-doc [~struct-sym ~arg]
           #_(let [valid-keys# #{~@(map #(keywordize (get % "name")) props)}]
               (assert (every? valid-keys#
                               (keys ~'m))
                       (str "Invalid key(s) passed to " ~(str merge-fname) ": "
                            (clojure.string/join
                             ", "
                             (remove valid-keys# (keys ~'m))))))
           ~set-properties
           ~struct-sym))
       (defn ~fname ~map-doc
         ([]
          ~constructor)
         ([~arg]
          (let [~struct-sym ~constructor]
            (~merge-fname ~struct-sym ~'m)))))))

(defmacro gen-wrappers []
  `(do
     ~@(->> (:structs api)
            (remove #(#{:clong/cef_dialog_handler_t
                        :clong/cef_display_handler_t}
                        (:id %)))
            (map gen-wrapper* ))))

(gen-wrappers)

(defmulti call (fn [obj k & args]
                 [(class obj) k]))

(defn ^:private call-method [struct-sym field]
  (let [[_ arg-types ret-type] (:datatype field)
        args (rest ;; skip struct arg
              (map (fn [i]
                     (gensym (str "arg-" i "-")))
                   (range (count arg-types))))
        res## (gensym "result-")]
    `(let [cls# (gen/coffi-type->jna struct-prefix ~ret-type)]
       (defmethod call [~struct-sym
                        ~(keyword (:name field))]
         [struct# _# ~@args]
         (let [~res##
               (.invoke (Function/getFunction
                         (~(-> field
                               :name
                               keyword)
                          struct#))
                        cls#
                        (to-array
                         [struct#
                          ~@(eduction
                             (map (fn [[type vsym]]
                                    (coercer type vsym)))
                             (map vector
                                  ;; struct already included
                                  (rest arg-types)
                                  args))]))]
           ~(uncoerce ret-type res##))))))

(defn ^:private call-methods [struct]
  (let [struct-name (-> struct
                        :id
                        name)
        struct-sym (symbol (str struct-name "ByReference"))]
   (into []
         (comp
          (filter (fn [{:keys [datatype]}]
                    (and (vector? datatype)
                         (= :coffi.ffi/fn (first datatype)))))
          (map #(call-method struct-sym %)))
         (:fields struct))))

(defmacro gen-call-methods []
  `(do
     ~@(->> (:structs api)
            (mapcat call-methods))))

(gen-call-methods)

(comment
  (def my-struct
    (->> api
         :structs
         (filter #(= ;;:clong/cef_life_span_handler_t
                   :clong/cef_frame_t
                   (:id %)))
         first))

  (clojure.pprint/pprint (call-methods my-struct))

  (clojure.pprint/pprint
   (gen-wrapper* my-struct))

  (def kls (com.phronemophobic.clong.gen.jna/coffi-type->jna
            com.phronemophobic.gen3/struct-prefix
            [:coffi.mem/pointer :clong/cef_render_handler_t]))
  (instance? kls (map->render-handler))


  ,)


