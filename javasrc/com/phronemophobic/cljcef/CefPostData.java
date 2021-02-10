// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefPostData extends Structure{
public CefPostData(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface IsReadOnlyFunc extends Callback {

int is_read_only(CefPostData x0); 
}

public static interface HasExcludedElementsFunc extends Callback {

int has_excluded_elements(CefPostData x0); 
}

public static interface GetElementCountFunc extends Callback {

SizeT get_element_count(CefPostData x0); 
}

public static interface GetElementsFunc extends Callback {

void get_elements(CefPostData x0, Pointer x1, Pointer x2); 
}

public static interface RemoveElementFunc extends Callback {

int remove_element(CefPostData x0, CefPostDataElement x1); 
}

public static interface AddElementFunc extends Callback {

int add_element(CefPostData x0, CefPostDataElement x1); 
}

public static interface RemoveElementsFunc extends Callback {

void remove_elements(CefPostData x0); 
}

public CefBaseRefCounted base;

public IsReadOnlyFunc is_read_only;

public HasExcludedElementsFunc has_excluded_elements;

public GetElementCountFunc get_element_count;

public GetElementsFunc get_elements;

public RemoveElementFunc remove_element;

public AddElementFunc add_element;

public RemoveElementsFunc remove_elements;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "is_read_only", "has_excluded_elements", "get_element_count", "get_elements", "remove_element", "add_element", "remove_elements");
 }

public int isReadOnly () {

return  this.is_read_only.is_read_only(this);

}

public int hasExcludedElements () {

return  this.has_excluded_elements.has_excluded_elements(this);

}

public SizeT getElementCount () {

return  this.get_element_count.get_element_count(this);

}

public void getElements (Pointer x1, Pointer x2) {

  this.get_elements.get_elements(this,  x1,  x2);

}

public int removeElement (CefPostDataElement x1) {

return  this.remove_element.remove_element(this,  x1);

}

public int addElement (CefPostDataElement x1) {

return  this.add_element.add_element(this,  x1);

}

public void removeElements () {

  this.remove_elements.remove_elements(this);

}}