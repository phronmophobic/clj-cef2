package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefBaseScoped extends Structure{


public static interface DelFunc extends Callback {

void del(CefBaseScoped x0); 
}

public SizeT size;

public DelFunc del;

protected List getFieldOrder() {
                                            return Arrays.asList("size", "del");
 }}