package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefAudioHandler extends Structure{
public CefAudioHandler(){
base.size.setValue(this.size());
}

public static interface GetAudioParametersFunc extends Callback {

int get_audio_parameters(CefAudioHandler x0, CefBrowser x1, CefAudioParameters x2); 
}

public static interface OnAudioStreamStartedFunc extends Callback {

void on_audio_stream_started(CefAudioHandler x0, CefBrowser x1, Pointer x2, int x3); 
}

public static interface OnAudioStreamPacketFunc extends Callback {

void on_audio_stream_packet(CefAudioHandler x0, CefBrowser x1, Pointer x2, int x3, long x4); 
}

public static interface OnAudioStreamStoppedFunc extends Callback {

void on_audio_stream_stopped(CefAudioHandler x0, CefBrowser x1); 
}

public static interface OnAudioStreamErrorFunc extends Callback {

void on_audio_stream_error(CefAudioHandler x0, CefBrowser x1, Pointer x2); 
}

public CefBaseRefCounted base;

public GetAudioParametersFunc get_audio_parameters;

public OnAudioStreamStartedFunc on_audio_stream_started;

public OnAudioStreamPacketFunc on_audio_stream_packet;

public OnAudioStreamStoppedFunc on_audio_stream_stopped;

public OnAudioStreamErrorFunc on_audio_stream_error;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_audio_parameters", "on_audio_stream_started", "on_audio_stream_packet", "on_audio_stream_stopped", "on_audio_stream_error");
 }}