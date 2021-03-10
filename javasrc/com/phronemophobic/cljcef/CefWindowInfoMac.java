// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefWindowInfoMac extends Structure{




public CefStringUtf16 window_name;

public int x;

public int y;

public int width;

public int height;

public int hidden;

public Pointer parent_view;

public int windowless_rendering_enabled;

public int shared_texture_enabled;

public int external_begin_frame_enabled;

public Pointer view;

protected List getFieldOrder() {
                                            return Arrays.asList("window_name", "x", "y", "width", "height", "hidden", "parent_view", "windowless_rendering_enabled", "shared_texture_enabled", "external_begin_frame_enabled", "view");
 }

}