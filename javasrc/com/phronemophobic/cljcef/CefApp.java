package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefApp extends Structure{
public CefApp(){
base.size.setValue(this.size());
}

public static interface OnBeforeCommandLineProcessingFunc extends Callback {

void on_before_command_line_processing(CefApp x0, Pointer x1, CefCommandLine x2); 
}

public static interface OnRegisterCustomSchemesFunc extends Callback {

void on_register_custom_schemes(CefApp x0, CefSchemeRegistrar x1); 
}

public static interface GetResourceBundleHandlerFunc extends Callback {

CefResourceBundleHandler get_resource_bundle_handler(CefApp x0); 
}

public static interface GetBrowserProcessHandlerFunc extends Callback {

CefBrowserProcessHandler get_browser_process_handler(CefApp x0); 
}

public static interface GetRenderProcessHandlerFunc extends Callback {

CefRenderProcessHandler get_render_process_handler(CefApp x0); 
}

public CefBaseRefCounted base;

public OnBeforeCommandLineProcessingFunc on_before_command_line_processing;

public OnRegisterCustomSchemesFunc on_register_custom_schemes;

public GetResourceBundleHandlerFunc get_resource_bundle_handler;

public GetBrowserProcessHandlerFunc get_browser_process_handler;

public GetRenderProcessHandlerFunc get_render_process_handler;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "on_before_command_line_processing", "on_register_custom_schemes", "get_resource_bundle_handler", "get_browser_process_handler", "get_render_process_handler");
 }}