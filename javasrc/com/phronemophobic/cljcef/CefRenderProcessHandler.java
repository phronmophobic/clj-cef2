// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefRenderProcessHandler extends Structure{
public CefRenderProcessHandler(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface OnWebKitInitializedFunc extends Callback {

void on_web_kit_initialized(CefRenderProcessHandler x0); 
}

public static interface OnBrowserCreatedFunc extends Callback {

void on_browser_created(CefRenderProcessHandler x0, CefBrowser x1, CefDictionaryValue x2); 
}

public static interface OnBrowserDestroyedFunc extends Callback {

void on_browser_destroyed(CefRenderProcessHandler x0, CefBrowser x1); 
}

public static interface GetLoadHandlerFunc extends Callback {

CefLoadHandler get_load_handler(CefRenderProcessHandler x0); 
}

public static interface OnContextCreatedFunc extends Callback {

void on_context_created(CefRenderProcessHandler x0, CefBrowser x1, CefFrame x2, CefV8context x3); 
}

public static interface OnContextReleasedFunc extends Callback {

void on_context_released(CefRenderProcessHandler x0, CefBrowser x1, CefFrame x2, CefV8context x3); 
}

public static interface OnUncaughtExceptionFunc extends Callback {

void on_uncaught_exception(CefRenderProcessHandler x0, CefBrowser x1, CefFrame x2, CefV8context x3, CefV8exception x4, CefV8stackTrace x5); 
}

public static interface OnFocusedNodeChangedFunc extends Callback {

void on_focused_node_changed(CefRenderProcessHandler x0, CefBrowser x1, CefFrame x2, CefDomnode x3); 
}

public static interface OnProcessMessageReceivedFunc extends Callback {

int on_process_message_received(CefRenderProcessHandler x0, CefBrowser x1, CefFrame x2, int x3, CefProcessMessage x4); 
}

public CefBaseRefCounted base;

public OnWebKitInitializedFunc on_web_kit_initialized;

public OnBrowserCreatedFunc on_browser_created;

public OnBrowserDestroyedFunc on_browser_destroyed;

public GetLoadHandlerFunc get_load_handler;

public OnContextCreatedFunc on_context_created;

public OnContextReleasedFunc on_context_released;

public OnUncaughtExceptionFunc on_uncaught_exception;

public OnFocusedNodeChangedFunc on_focused_node_changed;

public OnProcessMessageReceivedFunc on_process_message_received;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "on_web_kit_initialized", "on_browser_created", "on_browser_destroyed", "get_load_handler", "on_context_created", "on_context_released", "on_uncaught_exception", "on_focused_node_changed", "on_process_message_received");
 }

public void onWebKitInitialized () {

  this.on_web_kit_initialized.on_web_kit_initialized(this);

}

public void onBrowserCreated (CefBrowser x1, CefDictionaryValue x2) {

  this.on_browser_created.on_browser_created(this,  x1,  x2);

}

public void onBrowserDestroyed (CefBrowser x1) {

  this.on_browser_destroyed.on_browser_destroyed(this,  x1);

}

public CefLoadHandler getLoadHandler () {

return  this.get_load_handler.get_load_handler(this);

}

public void onContextCreated (CefBrowser x1, CefFrame x2, CefV8context x3) {

  this.on_context_created.on_context_created(this,  x1,  x2,  x3);

}

public void onContextReleased (CefBrowser x1, CefFrame x2, CefV8context x3) {

  this.on_context_released.on_context_released(this,  x1,  x2,  x3);

}

public void onUncaughtException (CefBrowser x1, CefFrame x2, CefV8context x3, CefV8exception x4, CefV8stackTrace x5) {

  this.on_uncaught_exception.on_uncaught_exception(this,  x1,  x2,  x3,  x4,  x5);

}

public void onFocusedNodeChanged (CefBrowser x1, CefFrame x2, CefDomnode x3) {

  this.on_focused_node_changed.on_focused_node_changed(this,  x1,  x2,  x3);

}

public int onProcessMessageReceived (CefBrowser x1, CefFrame x2, int x3, CefProcessMessage x4) {

return  this.on_process_message_received.on_process_message_received(this,  x1,  x2,  x3,  x4);

}}