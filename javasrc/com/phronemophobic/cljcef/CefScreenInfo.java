package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefScreenInfo extends Structure{




public float device_scale_factor;

public int depth;

public int depth_per_component;

public int is_monochrome;

public CefRect rect;

public CefRect available_rect;

protected List getFieldOrder() {
                                            return Arrays.asList("device_scale_factor", "depth", "depth_per_component", "is_monochrome", "rect", "available_rect");
 }}