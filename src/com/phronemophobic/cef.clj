(ns com.phronemophobic.cef
  "Clojure Wrappers for CEF"
  (:require [com.phronemophobic.gen3 :as gen3]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.java.shell :as sh]
            [com.phronemophobic.cef.env :as env]
            [com.phronemophobic.cinterop :as cinterop
             :refer [defc
                     preserve!
                     dispatch-sync
                     dispatch-async]]
            [com.phronemophobic.fs :as fs])
  (:import com.sun.jna.WString
           com.sun.jna.Memory
           com.sun.jna.Pointer
           com.sun.jna.Platform
           java.net.URLEncoder
           java.nio.file.Files
           java.nio.file.attribute.FileAttribute))

(defonce ^{:private true
           :no-doc true}
  prepared-environment (atom false))

(cinterop/defc BackupSignalHandlers cinterop/cljcef cinterop/void [])

(gen3/import-structs!)

(def ^:no-doc void Void/TYPE)

(defc change_bundle_path cinterop/cljcef void [bundle-path])
(defc change_bundle_identifier cinterop/cljcef void [bundle-identifier])
(defc printDirectory cinterop/cljcef void [])

(defc cef_execute_process cinterop/cef Integer/TYPE [main-args app ])
#_(defn cef-execute-process [main-args app]
  (cef_execute_process main-args app))

(defc _cef_load_library cinterop/cljcef Integer/TYPE [String])

(defc _cef_initialize cinterop/cljcef Integer/TYPE [args settings application sandbox-info])


(defc cef_run_message_loop cinterop/cef void [])
(defn cef-run-message-loop
  "Run the event loop that drives the browser.

  MUST be called from the main thread. The event loop will not stop on it's own.

  Run the CEF message loop. Use this function instead of an application-
  provided message loop to get the best balance between performance and CPU
  usage. This function should only be called on the main application thread and
  only if cef_initialize() is called with a
  CefSettings.multi_threaded_message_loop value of false (0). This function
  will block until a quit message is received by the system.

  Typically, the way to stop the event loop is to call `(.closeBrowser (.getHost browser) 1)` and install a life span handler. The life span handler will receive a `:on-before-close-event` where it can check if the last browser is being closed and then call `cef-quit-message-loop`. See `map->life-span-handler` for more info."
  []
  (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
  (cef_run_message_loop))

(defc cef_browser_host_create_browser cinterop/cef void [window-info client url browser-settings extra-info request-context])

(defn cef-browser-host-create-browser
  "Create a new browser window using the window parameters specified by
  |windowInfo|.

  MUST be called on the main thread.

  All values will be copied internally and the actual window will
  be created on the UI thread. If |request_context| is NULL the global request
  context will be used. This function can be called on any browser process
  thread and will not block. The optional |extra_info| parameter provides an
  opportunity to specify extra information specific to the created browser that
  will be passed to cef_render_process_handler_t::on_browser_created() in the
  render process."
  [window-info client url browser-settings extra-info request-context]
  (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
  (gen3/cef_browser_host_create_browser window-info client (when url (gen3/cef-string-ref url)) browser-settings  extra-info request-context))

;; (defc cef_browser_host_create_browser_sync cinterop/cef CefBrowser [window-info client url browser-settings extra-info request-context])

;; (defn cef-browser-host-create-browser-sync
;;   "Create a new browser window using the window parameters specified by
;; |windowInfo|. 

;;   MUST be called on the main thread.

;;   If |request_context| is NULL the global request context will be
;;   used. This function can only be called on the browser process UI thread. The
;;   optional |extra_info| parameter provides an opportunity to specify extra
;;   information specific to the created browser that will be passed to
;;   cef_render_process_handler_t::on_browser_created() in the render process."
;;   [window-info client url browser-settings extra-info request-context]
;;   (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
;;   (cef_browser_host_create_browser_sync window-info client (when url (cef-string url)) browser-settings extra-info request-context))



(defc cef_shutdown cinterop/cef void [])
(defn ^:no-doc cef-shutdown
  "This function should be called on the main application thread to shut down
  the CEF browser process before the application exits."
  []
  (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
  (cef_shutdown))





;; // This function should be called from the application entry point function to
;; // execute a secondary process. It can be used to run secondary processes from
;; // the browser client executable (default behavior) or from a separate
;; // executable specified by the CefSettings.browser_subprocess_path value. If
;; // called for the browser process (identified by no "type" command-line value)
;; // it will return immediately with a value of -1. If called for a recognized
;; // secondary process it will block until the process should exit and then return
;; // the process exit code. The |application| parameter may be NULL. The
;; // |windows_sandbox_info| parameter is only used on Windows and may be NULL (see
;; // cef_sandbox_win.h for details).
;; ///
;; CEF_EXPORT int cef_execute_process(const struct _cef_main_args_t* args,
;;                                    cef_app_t* application,
;;                                    void* windows_sandbox_info);


;; CEF_EXPORT int cef_initialize(const struct _cef_main_args_t* args,
;;                               const struct _cef_settings_t* settings,
;;                               cef_app_t* application,
;;                               void* windows_sandbox_info);

;; ///
;; // This function should be called on the main application thread to shut down
;; // the CEF browser process before the application exits.
;; ///
;; CEF_EXPORT void cef_shutdown();

;; ///

;; ///
;; CEF_EXPORT void cef_do_message_loop_work();

(defc cef_do_message_loop_work cinterop/cef void [])
(defn cef-do-message-loop-work
  "Perform a single iteration of CEF message loop processing.

  MUST be called from the main thread.

  This function is provided for cases where the CEF message loop must be integrated into an
existing application message loop. Use of this function is not recommended
for most users; use either the cef_run_message_loop() function or
CefSettings.multi_threaded_message_loop if possible. When using this function
care must be taken to balance performance against excessive CPU usage. It is
recommended to enable the CefSettings.external_message_pump option when using
this function so that
cef_browser_process_handler_t::on_schedule_message_pump_work() callbacks can
facilitate the scheduling process. This function should only be called on the
main application thread and only if cef_initialize() is called with a
CefSettings.multi_threaded_message_loop value of false (0). This function
will not block."
  []
  (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
  (cef_do_message_loop_work))

;; ///

;; ///
;; CEF_EXPORT void cef_run_message_loop();

;; ///
;; ///
;; CEF_EXPORT void cef_quit_message_loop();

(defc cef_quit_message_loop cinterop/cef void [])
(defn cef-quit-message-loop
  "Quit the CEF message loop that was started by calling cef_run_message_loop().

  MUST be called from the main thread.

  This function should only be called on the main application thread and only
  if cef_run_message_loop() was used."
  []
  (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
  (cef_quit_message_loop))

;; ///
;; // Set to true (1) before calling Windows APIs like TrackPopupMenu that enter a
;; // modal message loop. Set to false (0) after exiting the modal message loop.
;; ///
;; CEF_EXPORT void cef_set_osmodal_loop(int osModalLoop);

;; ///
;; // Call during process startup to enable High-DPI support on Windows 7 or newer.
;; // Older versions of Windows should be left DPI-unaware because they do not
;; // support DirectWrite and GDI fonts are kerned very badly.
;; ///
;; CEF_EXPORT void cef_enable_highdpi_support();



(defn cef-initialize
  "This function should be called on the main application thread to initialize
  the CEF browser process. The |application| parameter may be NULL. A return
  value of true (1) indicates that it succeeded and false (0) indicates that it
  failed. The |windows_sandbox_info| parameter is only used on Windows and may
  be NULL (see cef_sandbox_win.h for details)."

  ([app]
   (cef-initialize app env/default-target-dir))
  ([app target-dir]
   (cef-initialize (gen3/map->main-args)
                   (gen3/map->settings
                    {:framework-dir-path (.getAbsolutePath (io/file target-dir "Chromium Embedded Framework.framework"))
                     :browser-subprocess-path (.getAbsolutePath (io/file target-dir "ceflib Helper"))
                     :main-bundle-path (.getAbsolutePath target-dir)
                     :locales-dir-path (.getAbsolutePath (io/file target-dir "locales"))
                     ;; :no-sandbox 1
                     :windowless-rendering-enabled 1
                     })
                   app
                   nil))
  ([main-args settings app sandbox-info]
   (assert @prepared-environment "Did you call download-and-prepare-environment! yet?")
   (_cef_initialize main-args settings app sandbox-info)))



;; ///
;; // Returns the task runner for the current thread. Only CEF threads will have
;; // task runners. An NULL reference will be returned if this function is called
;; // on an invalid thread.
;; ///
;; CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_current_thread();

;; ///
;; // Returns the task runner for the specified CEF thread.
;; ///
;; CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_thread(
;;     cef_thread_id_t threadId);

;; ///
;; // Returns true (1) if called on the specified thread. Equivalent to using
;; // cef_task_runner_t::GetForThread(threadId)->belongs_to_current_thread().
;; ///
;; CEF_EXPORT int cef_currently_on(cef_thread_id_t threadId);

;; ///
;; // Post a task for execution on the specified thread. Equivalent to using
;; // cef_task_runner_t::GetForThread(threadId)->PostTask(task).
;; ///
;; CEF_EXPORT int cef_post_task(cef_thread_id_t threadId, cef_task_t* task);
(defc cef_post_task cinterop/cef Integer/TYPE [thread-id task])

(defn post-task-to-main [f]
  (cef_post_task 0
                 (gen3/map->task
                  {:execute
                   (fn [this]
                     (f))})))


;; ///
;; // Post a task for delayed execution on the specified thread. Equivalent to
;; // using cef_task_runner_t::GetForThread(threadId)->PostDelayedTask(task,
;; // delay_ms).
;; ///
;; CEF_EXPORT int cef_post_delayed_task(cef_thread_id_t threadId,
;;                                      cef_task_t* task,
;;                                      int64 delay_ms);



(defn prepare-environment-macosx!
  ([target-dir]
   (when-not @prepared-environment
     (let [framework-file (io/file target-dir
                                   "Chromium Embedded Framework.framework")]
       (assert (.exists framework-file)
               (str "Chromium Embedded Framework.framework not found at " (.getAbsolutePath framework-file) "\nDid you run download-and-extract-framework?")))
     (env/extract-helper target-dir)

     (_cef_load_library
      (.getAbsolutePath
       (io/file target-dir
                "Chromium Embedded Framework.framework"
                "Chromium Embedded Framework")
       ))

     (change_bundle_path (.getAbsolutePath (io/as-file target-dir)))
     ;; (change_bundle_identifier "foobar")
     ;; (printDirectory)
     (BackupSignalHandlers)
     (reset! prepared-environment true)
     nil)
   ))



(defn prepare-environment-linux!
  ([target-dir]
   (when-not @prepared-environment
     (doseq [prop ["jna.library.path"]
             :let [jna-paths (some-> (System/getProperty prop)
                                     (clojure.string/split #":")
                                     (into #{}))]]
       (when (not (contains? jna-paths target-dir))
         (System/setProperty prop
                             (if-let [s (System/getProperty prop)]
                               (str s ":" (.getAbsolutePath target-dir))
                               (.getAbsolutePath target-dir)))))
     (BackupSignalHandlers)
     (reset! prepared-environment true))
   nil))


(defn prepare-environment!
  ([]
   (prepare-environment!
    (doto env/default-target-dir
      (.mkdir))))
  ([target-dir]
   (if (Platform/isLinux)
     (prepare-environment-linux! target-dir)
     (prepare-environment-macosx! target-dir))))

(defn download-and-prepare-environment!
  "The Chromium Embedded Framework is about 90MB compressed and 234MB uncompressed which makes it impractical to include in the library jar.

  Download the framework and doing some other environment setup required by cef."
  ([]
   (download-and-prepare-environment!
    (doto env/default-target-dir
      (.mkdir))))
  ([target-dir]
   (when-not @prepared-environment
     (env/download-and-extract-framework target-dir)
     (prepare-environment! target-dir)
     nil)))
