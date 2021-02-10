// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefAuthCallback extends Structure{
public CefAuthCallback(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface ContFunc extends Callback {

void cont(CefAuthCallback x0, CefStringUtf16 x1, CefStringUtf16 x2); 
}

public static interface CancelFunc extends Callback {

void cancel(CefAuthCallback x0); 
}

public CefBaseRefCounted base;

public ContFunc cont;

public CancelFunc cancel;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "cont", "cancel");
 }

public void cont (String x1, String x2) {

  this.cont.cont(this, CljCefLib.toCefString(x1), CljCefLib.toCefString(x2));

}

public void cancel () {

  this.cancel.cancel(this);

}}