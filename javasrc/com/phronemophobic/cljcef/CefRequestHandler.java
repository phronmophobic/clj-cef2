// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefRequestHandler extends Structure{
public CefRequestHandler(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface OnBeforeBrowseFunc extends Callback {

int on_before_browse(CefRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, int x4, int x5); 
}

public static interface OnOpenUrlfromTabFunc extends Callback {

int on_open_urlfrom_tab(CefRequestHandler x0, CefBrowser x1, CefFrame x2, CefStringUtf16 x3, int x4, int x5); 
}

public static interface GetResourceRequestHandlerFunc extends Callback {

CefResourceRequestHandler get_resource_request_handler(CefRequestHandler x0, CefBrowser x1, CefFrame x2, CefRequest x3, int x4, int x5, CefStringUtf16 x6, Pointer x7); 
}

public static interface GetAuthCredentialsFunc extends Callback {

int get_auth_credentials(CefRequestHandler x0, CefBrowser x1, CefStringUtf16 x2, int x3, CefStringUtf16 x4, int x5, CefStringUtf16 x6, CefStringUtf16 x7, CefAuthCallback x8); 
}

public static interface OnQuotaRequestFunc extends Callback {

int on_quota_request(CefRequestHandler x0, CefBrowser x1, CefStringUtf16 x2, long x3, CefRequestCallback x4); 
}

public static interface OnCertificateErrorFunc extends Callback {

int on_certificate_error(CefRequestHandler x0, CefBrowser x1, int x2, CefStringUtf16 x3, CefSslinfo x4, CefRequestCallback x5); 
}

public static interface OnSelectClientCertificateFunc extends Callback {

int on_select_client_certificate(CefRequestHandler x0, CefBrowser x1, int x2, CefStringUtf16 x3, int x4, SizeT x5, Pointer x6, CefSelectClientCertificateCallback x7); 
}

public static interface OnPluginCrashedFunc extends Callback {

void on_plugin_crashed(CefRequestHandler x0, CefBrowser x1, CefStringUtf16 x2); 
}

public static interface OnRenderViewReadyFunc extends Callback {

void on_render_view_ready(CefRequestHandler x0, CefBrowser x1); 
}

public static interface OnRenderProcessTerminatedFunc extends Callback {

void on_render_process_terminated(CefRequestHandler x0, CefBrowser x1, int x2); 
}

public static interface OnDocumentAvailableInMainFrameFunc extends Callback {

void on_document_available_in_main_frame(CefRequestHandler x0, CefBrowser x1); 
}

public CefBaseRefCounted base;

public OnBeforeBrowseFunc on_before_browse;

public OnOpenUrlfromTabFunc on_open_urlfrom_tab;

public GetResourceRequestHandlerFunc get_resource_request_handler;

public GetAuthCredentialsFunc get_auth_credentials;

public OnQuotaRequestFunc on_quota_request;

public OnCertificateErrorFunc on_certificate_error;

public OnSelectClientCertificateFunc on_select_client_certificate;

public OnPluginCrashedFunc on_plugin_crashed;

public OnRenderViewReadyFunc on_render_view_ready;

public OnRenderProcessTerminatedFunc on_render_process_terminated;

public OnDocumentAvailableInMainFrameFunc on_document_available_in_main_frame;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "on_before_browse", "on_open_urlfrom_tab", "get_resource_request_handler", "get_auth_credentials", "on_quota_request", "on_certificate_error", "on_select_client_certificate", "on_plugin_crashed", "on_render_view_ready", "on_render_process_terminated", "on_document_available_in_main_frame");
 }

public int onBeforeBrowse (CefBrowser x1, CefFrame x2, CefRequest x3, int x4, int x5) {

return  this.on_before_browse.on_before_browse(this,  x1,  x2,  x3,  x4,  x5);

}

public int onOpenUrlfromTab (CefBrowser x1, CefFrame x2, String x3, int x4, int x5) {

return  this.on_open_urlfrom_tab.on_open_urlfrom_tab(this,  x1,  x2, CljCefLib.toCefString(x3),  x4,  x5);

}

public CefResourceRequestHandler getResourceRequestHandler (CefBrowser x1, CefFrame x2, CefRequest x3, int x4, int x5, String x6, Pointer x7) {

return  this.get_resource_request_handler.get_resource_request_handler(this,  x1,  x2,  x3,  x4,  x5, CljCefLib.toCefString(x6),  x7);

}

public int getAuthCredentials (CefBrowser x1, String x2, int x3, String x4, int x5, String x6, String x7, CefAuthCallback x8) {

return  this.get_auth_credentials.get_auth_credentials(this,  x1, CljCefLib.toCefString(x2),  x3, CljCefLib.toCefString(x4),  x5, CljCefLib.toCefString(x6), CljCefLib.toCefString(x7),  x8);

}

public int onQuotaRequest (CefBrowser x1, String x2, long x3, CefRequestCallback x4) {

return  this.on_quota_request.on_quota_request(this,  x1, CljCefLib.toCefString(x2),  x3,  x4);

}

public int onCertificateError (CefBrowser x1, int x2, String x3, CefSslinfo x4, CefRequestCallback x5) {

return  this.on_certificate_error.on_certificate_error(this,  x1,  x2, CljCefLib.toCefString(x3),  x4,  x5);

}

public int onSelectClientCertificate (CefBrowser x1, int x2, String x3, int x4, SizeT x5, Pointer x6, CefSelectClientCertificateCallback x7) {

return  this.on_select_client_certificate.on_select_client_certificate(this,  x1,  x2, CljCefLib.toCefString(x3),  x4,  x5,  x6,  x7);

}

public void onPluginCrashed (CefBrowser x1, String x2) {

  this.on_plugin_crashed.on_plugin_crashed(this,  x1, CljCefLib.toCefString(x2));

}

public void onRenderViewReady (CefBrowser x1) {

  this.on_render_view_ready.on_render_view_ready(this,  x1);

}

public void onRenderProcessTerminated (CefBrowser x1, int x2) {

  this.on_render_process_terminated.on_render_process_terminated(this,  x1,  x2);

}

public void onDocumentAvailableInMainFrame (CefBrowser x1) {

  this.on_document_available_in_main_frame.on_document_available_in_main_frame(this,  x1);

}}