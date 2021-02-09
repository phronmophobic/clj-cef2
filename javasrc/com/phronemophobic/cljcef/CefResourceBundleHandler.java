package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefResourceBundleHandler extends Structure{
public CefResourceBundleHandler(){
base.size.setValue(this.size());
}

public static interface GetLocalizedStringFunc extends Callback {

int get_localized_string(CefResourceBundleHandler x0, int x1, CefStringUtf16 x2); 
}

public static interface GetDataResourceFunc extends Callback {

int get_data_resource(CefResourceBundleHandler x0, int x1, Pointer x2, Pointer x3); 
}

public static interface GetDataResourceForScaleFunc extends Callback {

int get_data_resource_for_scale(CefResourceBundleHandler x0, int x1, int x2, Pointer x3, Pointer x4); 
}

public CefBaseRefCounted base;

public GetLocalizedStringFunc get_localized_string;

public GetDataResourceFunc get_data_resource;

public GetDataResourceForScaleFunc get_data_resource_for_scale;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_localized_string", "get_data_resource", "get_data_resource_for_scale");
 }}