package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefCompositionUnderline extends Structure{




public CefRange range;

public int color;

public int background_color;

public int thick;

public int style;

protected List getFieldOrder() {
                                            return Arrays.asList("range", "color", "background_color", "thick", "style");
 }}