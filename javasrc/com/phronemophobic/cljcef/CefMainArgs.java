package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefMainArgs extends Structure{




public int argc;

public Pointer argv;

protected List getFieldOrder() {
                                            return Arrays.asList("argc", "argv");
 }}