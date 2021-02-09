package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefBrowserSettings extends Structure{




public SizeT size;

public int windowless_frame_rate;

public CefStringUtf16 standard_font_family;

public CefStringUtf16 fixed_font_family;

public CefStringUtf16 serif_font_family;

public CefStringUtf16 sans_serif_font_family;

public CefStringUtf16 cursive_font_family;

public CefStringUtf16 fantasy_font_family;

public int default_font_size;

public int default_fixed_font_size;

public int minimum_font_size;

public int minimum_logical_font_size;

public CefStringUtf16 default_encoding;

public int remote_fonts;

public int javascript;

public int javascript_close_windows;

public int javascript_access_clipboard;

public int javascript_dom_paste;

public int plugins;

public int universal_access_from_file_urls;

public int file_access_from_file_urls;

public int web_security;

public int image_loading;

public int image_shrink_standalone_to_fit;

public int text_area_resize;

public int tab_to_links;

public int local_storage;

public int databases;

public int application_cache;

public int webgl;

public int background_color;

public CefStringUtf16 accept_language_list;

protected List getFieldOrder() {
                                            return Arrays.asList("size", "windowless_frame_rate", "standard_font_family", "fixed_font_family", "serif_font_family", "sans_serif_font_family", "cursive_font_family", "fantasy_font_family", "default_font_size", "default_fixed_font_size", "minimum_font_size", "minimum_logical_font_size", "default_encoding", "remote_fonts", "javascript", "javascript_close_windows", "javascript_access_clipboard", "javascript_dom_paste", "plugins", "universal_access_from_file_urls", "file_access_from_file_urls", "web_security", "image_loading", "image_shrink_standalone_to_fit", "text_area_resize", "tab_to_links", "local_storage", "databases", "application_cache", "webgl", "background_color", "accept_language_list");
 }}