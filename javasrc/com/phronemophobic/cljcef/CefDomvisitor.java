package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefDomvisitor extends Structure{
public CefDomvisitor(){
base.size.setValue(this.size());
}

public static interface VisitFunc extends Callback {

void visit(CefDomvisitor x0, CefDomdocument x1); 
}

public CefBaseRefCounted base;

public VisitFunc visit;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "visit");
 }}