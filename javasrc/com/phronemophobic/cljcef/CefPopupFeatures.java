package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefPopupFeatures extends Structure{




public int x;

public int xSet;

public int y;

public int ySet;

public int width;

public int widthSet;

public int height;

public int heightSet;

public int menuBarVisible;

public int statusBarVisible;

public int toolBarVisible;

public int scrollbarsVisible;

protected List getFieldOrder() {
                                            return Arrays.asList("x", "xSet", "y", "ySet", "width", "widthSet", "height", "heightSet", "menuBarVisible", "statusBarVisible", "toolBarVisible", "scrollbarsVisible");
 }}