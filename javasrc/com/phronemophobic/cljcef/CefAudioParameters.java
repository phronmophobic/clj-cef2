package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefAudioParameters extends Structure{




public int channel_layout;

public int sample_rate;

public int frames_per_buffer;

protected List getFieldOrder() {
                                            return Arrays.asList("channel_layout", "sample_rate", "frames_per_buffer");
 }}