// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefKeyboardHandler extends Structure{
public CefKeyboardHandler(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface OnPreKeyEventFunc extends Callback {

int on_pre_key_event(CefKeyboardHandler x0, CefBrowser x1, CefKeyEvent x2, Pointer x3, Pointer x4); 
}

public static interface OnKeyEventFunc extends Callback {

int on_key_event(CefKeyboardHandler x0, CefBrowser x1, CefKeyEvent x2, Pointer x3); 
}

public CefBaseRefCounted base;

public OnPreKeyEventFunc on_pre_key_event;

public OnKeyEventFunc on_key_event;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "on_pre_key_event", "on_key_event");
 }

public int onPreKeyEvent (CefBrowser x1, CefKeyEvent x2, Pointer x3, Pointer x4) {

return  this.on_pre_key_event.on_pre_key_event(this,  x1,  x2,  x3,  x4);

}

public int onKeyEvent (CefBrowser x1, CefKeyEvent x2, Pointer x3) {

return  this.on_key_event.on_key_event(this,  x1,  x2,  x3);

}}