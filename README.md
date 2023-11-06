# clj-cef

Clojure bindings for the Chromium Embedded Framework


## Rationale


From <https://bitbucket.org/chromiumembedded/cef/src/master/>

> Some use cases for CEF include:
> 
>- Embedding an HTML5-compliant Web browser control in an existing native application.
>- Creating a light-weight native “shell” application that hosts a user interface developed primarily using Web technologies.
>- Rendering Web content “off-screen” in applications that have their own custom drawing frameworks.
>- Acting as a host for automated testing of existing Web properties and applications.

The purpose of clj-cef is to make all of these features available from clojure. The current priority is to expose as much cef functionality as possible. As such, the current API tries to match the underlying cef c api as closely as possible. Future versions or projects may provide more idiomatic clojure wrapping.


Currently, clj-cef will run on Mac OSX and linux.

## Threading

In general, most cef functions expect to be called on **the** main thread unless otherwise documented. On Mac OSX, the main thread is a very specific thread. If cef functions are called on the wrong thread, it will crash the jvm.

Tasks can be run on the main thread using `com.phronemophobic.cinterop/dispatch-sync` or `com.phronemophobic.cinterop/dispatch-async`.

```clojure

(require '[com.phronemophobic.cinterop
           :refer [dispatch-sync
                   dispatch-async]])

;; synchronous. will return when the task completes
(dispatch-sync
 (fn []
   (cef/prepare-environment!)))

;; asynchronous. will return immediately
(dispatch-async
 (fn []
   (cef/prepare-environment!)))
```

## Linux Dependencies

```sh
apt install xvfb libatk1.0-dev libatk-bridge2.0-dev libxkbcommon-dev libxcomposite-dev libxrandr-dev libgbm-dev libxdamage-dev
```

When running cef on linux without a display, use xvfb. The easiest way to use xvfb is to prefix command line commands with `xvfb-run`. See https://magpcss.org/ceforum/viewtopic.php?t=16993 for more information. 


# License

Copyright 2021 Adrian Smith. clj-cef is licensed under Apache License v2.0.
