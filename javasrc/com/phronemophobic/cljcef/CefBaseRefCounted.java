package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefBaseRefCounted extends Structure{


public static interface AddRefFunc extends Callback {

void add_ref(CefBaseRefCounted x0); 
}

public static interface ReleaseFunc extends Callback {

int release(CefBaseRefCounted x0); 
}

public static interface HasOneRefFunc extends Callback {

int has_one_ref(CefBaseRefCounted x0); 
}

public static interface HasAtLeastOneRefFunc extends Callback {

int has_at_least_one_ref(CefBaseRefCounted x0); 
}

public SizeT size;

public AddRefFunc add_ref;

public ReleaseFunc release;

public HasOneRefFunc has_one_ref;

public HasAtLeastOneRefFunc has_at_least_one_ref;

protected List getFieldOrder() {
                                            return Arrays.asList("size", "add_ref", "release", "has_one_ref", "has_at_least_one_ref");
 }}