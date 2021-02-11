// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefResourceRequestHandler extends Structure{
public CefResourceRequestHandler(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface GetCookieAccessFilterFunc extends Callback {

CefCookieAccessFilter get_cookie_access_filter(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3); 
}

public static interface OnBeforeResourceLoadFunc extends Callback {

int on_before_resource_load(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, CefRequestCallback x4); 
}

public static interface GetResourceHandlerFunc extends Callback {

CefResourceHandler get_resource_handler(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3); 
}

public static interface OnResourceRedirectFunc extends Callback {

void on_resource_redirect(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4, CefStringUtf16 x5); 
}

public static interface OnResourceResponseFunc extends Callback {

int on_resource_response(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4); 
}

public static interface GetResourceResponseFilterFunc extends Callback {

CefResponseFilter get_resource_response_filter(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4); 
}

public static interface OnResourceLoadCompleteFunc extends Callback {

void on_resource_load_complete(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4, int x5, long x6); 
}

public static interface OnProtocolExecutionFunc extends Callback {

void on_protocol_execution(CefResourceRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, Pointer x4); 
}

public CefBaseRefCounted base;

public GetCookieAccessFilterFunc get_cookie_access_filter;

public OnBeforeResourceLoadFunc on_before_resource_load;

public GetResourceHandlerFunc get_resource_handler;

public OnResourceRedirectFunc on_resource_redirect;

public OnResourceResponseFunc on_resource_response;

public GetResourceResponseFilterFunc get_resource_response_filter;

public OnResourceLoadCompleteFunc on_resource_load_complete;

public OnProtocolExecutionFunc on_protocol_execution;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_cookie_access_filter", "on_before_resource_load", "get_resource_handler", "on_resource_redirect", "on_resource_response", "get_resource_response_filter", "on_resource_load_complete", "on_protocol_execution");
 }

public CefCookieAccessFilter getCookieAccessFilter (CefBrowser x1, CefFrame x2, CefRequest x3) {

return  this.get_cookie_access_filter.get_cookie_access_filter(this,  x1,  x2,  x3);

}

public int onBeforeResourceLoad (CefBrowser x1, CefFrame x2, CefRequest x3, CefRequestCallback x4) {

return  this.on_before_resource_load.on_before_resource_load(this,  x1,  x2,  x3,  x4);

}

public CefResourceHandler getResourceHandler (CefBrowser x1, CefFrame x2, CefRequest x3) {

return  this.get_resource_handler.get_resource_handler(this,  x1,  x2,  x3);

}

public void onResourceRedirect (CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4, String x5) {

  this.on_resource_redirect.on_resource_redirect(this,  x1,  x2,  x3,  x4, CljCefLib.toCefString(x5));

}

public int onResourceResponse (CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4) {

return  this.on_resource_response.on_resource_response(this,  x1,  x2,  x3,  x4);

}

public CefResponseFilter getResourceResponseFilter (CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4) {

return  this.get_resource_response_filter.get_resource_response_filter(this,  x1,  x2,  x3,  x4);

}

public void onResourceLoadComplete (CefBrowser x1, CefFrame x2, CefRequest x3, CefResponse x4, int x5, long x6) {

  this.on_resource_load_complete.on_resource_load_complete(this,  x1,  x2,  x3,  x4,  x5,  x6);

}

public void onProtocolExecution (CefBrowser x1, CefFrame x2, CefRequest x3, Pointer x4) {

  this.on_protocol_execution.on_protocol_execution(this,  x1,  x2,  x3,  x4);

}}