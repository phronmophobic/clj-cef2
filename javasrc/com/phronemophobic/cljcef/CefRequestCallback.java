// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefRequestCallback extends Structure{
public CefRequestCallback(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface ContFunc extends Callback {

void cont(CefRequestCallback x0, int x1); 
}

public static interface CancelFunc extends Callback {

void cancel(CefRequestCallback x0); 
}

public CefBaseRefCounted base;

public ContFunc cont;

public CancelFunc cancel;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "cont", "cancel");
 }

public void cont (int x1) {

  this.cont.cont(this,  x1);

}

public void cancel () {

  this.cancel.cancel(this);

}}