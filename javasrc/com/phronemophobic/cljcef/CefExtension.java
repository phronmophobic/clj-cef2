package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefExtension extends Structure{
public CefExtension(){
base.size.setValue(this.size());
}

public static interface GetIdentifierFunc extends Callback {

CefStringUtf16 get_identifier(CefExtension x0); 
}

public static interface GetPathFunc extends Callback {

CefStringUtf16 get_path(CefExtension x0); 
}

public static interface GetManifestFunc extends Callback {

CefDictionaryValue get_manifest(CefExtension x0); 
}

public static interface IsSameFunc extends Callback {

int is_same(CefExtension x0, CefExtension x1); 
}

public static interface GetHandlerFunc extends Callback {

CefExtensionHandler get_handler(CefExtension x0); 
}

public static interface GetLoaderContextFunc extends Callback {

CefRequestContext get_loader_context(CefExtension x0); 
}

public static interface IsLoadedFunc extends Callback {

int is_loaded(CefExtension x0); 
}

public static interface UnloadFunc extends Callback {

void unload(CefExtension x0); 
}

public CefBaseRefCounted base;

public GetIdentifierFunc get_identifier;

public GetPathFunc get_path;

public GetManifestFunc get_manifest;

public IsSameFunc is_same;

public GetHandlerFunc get_handler;

public GetLoaderContextFunc get_loader_context;

public IsLoadedFunc is_loaded;

public UnloadFunc unload;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_identifier", "get_path", "get_manifest", "is_same", "get_handler", "get_loader_context", "is_loaded", "unload");
 }}