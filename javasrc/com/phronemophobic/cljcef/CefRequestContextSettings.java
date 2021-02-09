package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefRequestContextSettings extends Structure{




public SizeT size;

public CefStringUtf16 cache_path;

public int persist_session_cookies;

public int persist_user_preferences;

public int ignore_certificate_errors;

public CefStringUtf16 accept_language_list;

protected List getFieldOrder() {
                                            return Arrays.asList("size", "cache_path", "persist_session_cookies", "persist_user_preferences", "ignore_certificate_errors", "accept_language_list");
 }}