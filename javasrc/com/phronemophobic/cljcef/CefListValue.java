// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefListValue extends Structure{
public CefListValue(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface IsValidFunc extends Callback {

int is_valid(CefListValue x0); 
}

public static interface IsOwnedFunc extends Callback {

int is_owned(CefListValue x0); 
}

public static interface IsReadOnlyFunc extends Callback {

int is_read_only(CefListValue x0); 
}

public static interface IsSameFunc extends Callback {

int is_same(CefListValue x0, CefListValue x1); 
}

public static interface IsEqualFunc extends Callback {

int is_equal(CefListValue x0, CefListValue x1); 
}

public static interface CopyFunc extends Callback {

CefListValue copy(CefListValue x0); 
}

public static interface SetSizeFunc extends Callback {

int set_size(CefListValue x0, SizeT x1); 
}

public static interface GetSizeFunc extends Callback {

SizeT get_size(CefListValue x0); 
}

public static interface ClearFunc extends Callback {

int clear(CefListValue x0); 
}

public static interface RemoveFunc extends Callback {

int remove(CefListValue x0, SizeT x1); 
}

public static interface GetTypeFunc extends Callback {

int get_type(CefListValue x0, SizeT x1); 
}

public static interface GetValueFunc extends Callback {

CefValue get_value(CefListValue x0, SizeT x1); 
}

public static interface GetBoolFunc extends Callback {

int get_bool(CefListValue x0, SizeT x1); 
}

public static interface GetIntFunc extends Callback {

int get_int(CefListValue x0, SizeT x1); 
}

public static interface GetDoubleFunc extends Callback {

double get_double(CefListValue x0, SizeT x1); 
}

public static interface GetStringFunc extends Callback {

CefStringUtf16 get_string(CefListValue x0, SizeT x1); 
}

public static interface GetBinaryFunc extends Callback {

CefBinaryValue get_binary(CefListValue x0, SizeT x1); 
}

public static interface GetDictionaryFunc extends Callback {

CefDictionaryValue get_dictionary(CefListValue x0, SizeT x1); 
}

public static interface GetListFunc extends Callback {

CefListValue get_list(CefListValue x0, SizeT x1); 
}

public static interface SetValueFunc extends Callback {

int set_value(CefListValue x0, SizeT x1, CefValue x2); 
}

public static interface SetNullFunc extends Callback {

int set_null(CefListValue x0, SizeT x1); 
}

public static interface SetBoolFunc extends Callback {

int set_bool(CefListValue x0, SizeT x1, int x2); 
}

public static interface SetIntFunc extends Callback {

int set_int(CefListValue x0, SizeT x1, int x2); 
}

public static interface SetDoubleFunc extends Callback {

int set_double(CefListValue x0, SizeT x1, double x2); 
}

public static interface SetStringFunc extends Callback {

int set_string(CefListValue x0, SizeT x1, CefStringUtf16 x2); 
}

public static interface SetBinaryFunc extends Callback {

int set_binary(CefListValue x0, SizeT x1, CefBinaryValue x2); 
}

public static interface SetDictionaryFunc extends Callback {

int set_dictionary(CefListValue x0, SizeT x1, CefDictionaryValue x2); 
}

public static interface SetListFunc extends Callback {

int set_list(CefListValue x0, SizeT x1, CefListValue x2); 
}

public CefBaseRefCounted base;

public IsValidFunc is_valid;

public IsOwnedFunc is_owned;

public IsReadOnlyFunc is_read_only;

public IsSameFunc is_same;

public IsEqualFunc is_equal;

public CopyFunc copy;

public SetSizeFunc set_size;

public GetSizeFunc get_size;

public ClearFunc clear;

public RemoveFunc remove;

public GetTypeFunc get_type;

public GetValueFunc get_value;

public GetBoolFunc get_bool;

public GetIntFunc get_int;

public GetDoubleFunc get_double;

public GetStringFunc get_string;

public GetBinaryFunc get_binary;

public GetDictionaryFunc get_dictionary;

public GetListFunc get_list;

public SetValueFunc set_value;

public SetNullFunc set_null;

public SetBoolFunc set_bool;

public SetIntFunc set_int;

public SetDoubleFunc set_double;

public SetStringFunc set_string;

public SetBinaryFunc set_binary;

public SetDictionaryFunc set_dictionary;

public SetListFunc set_list;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "is_valid", "is_owned", "is_read_only", "is_same", "is_equal", "copy", "set_size", "get_size", "clear", "remove", "get_type", "get_value", "get_bool", "get_int", "get_double", "get_string", "get_binary", "get_dictionary", "get_list", "set_value", "set_null", "set_bool", "set_int", "set_double", "set_string", "set_binary", "set_dictionary", "set_list");
 }

public int isValid () {

return  this.is_valid.is_valid(this);

}

public int isOwned () {

return  this.is_owned.is_owned(this);

}

public int isReadOnly () {

return  this.is_read_only.is_read_only(this);

}

public int isSame (CefListValue x1) {

return  this.is_same.is_same(this,  x1);

}

public int isEqual (CefListValue x1) {

return  this.is_equal.is_equal(this,  x1);

}

public CefListValue copy () {

return  this.copy.copy(this);

}

public int setSize (SizeT x1) {

return  this.set_size.set_size(this,  x1);

}

public SizeT getSize () {

return  this.get_size.get_size(this);

}



public int remove (SizeT x1) {

return  this.remove.remove(this,  x1);

}

public int getType (SizeT x1) {

return  this.get_type.get_type(this,  x1);

}

public CefValue getValue (SizeT x1) {

return  this.get_value.get_value(this,  x1);

}

public int getBool (SizeT x1) {

return  this.get_bool.get_bool(this,  x1);

}

public int getInt (SizeT x1) {

return  this.get_int.get_int(this,  x1);

}

public double getDouble (SizeT x1) {

return  this.get_double.get_double(this,  x1);

}

public CefStringUtf16 getString (SizeT x1) {

return  this.get_string.get_string(this,  x1);

}

public CefBinaryValue getBinary (SizeT x1) {

return  this.get_binary.get_binary(this,  x1);

}

public CefDictionaryValue getDictionary (SizeT x1) {

return  this.get_dictionary.get_dictionary(this,  x1);

}

public CefListValue getList (SizeT x1) {

return  this.get_list.get_list(this,  x1);

}

public int setValue (SizeT x1, CefValue x2) {

return  this.set_value.set_value(this,  x1,  x2);

}

public int setNull (SizeT x1) {

return  this.set_null.set_null(this,  x1);

}

public int setBool (SizeT x1, int x2) {

return  this.set_bool.set_bool(this,  x1,  x2);

}

public int setInt (SizeT x1, int x2) {

return  this.set_int.set_int(this,  x1,  x2);

}

public int setDouble (SizeT x1, double x2) {

return  this.set_double.set_double(this,  x1,  x2);

}

public int setString (SizeT x1, String x2) {

return  this.set_string.set_string(this,  x1, CljCefLib.toCefString(x2));

}

public int setBinary (SizeT x1, CefBinaryValue x2) {

return  this.set_binary.set_binary(this,  x1,  x2);

}

public int setDictionary (SizeT x1, CefDictionaryValue x2) {

return  this.set_dictionary.set_dictionary(this,  x1,  x2);

}

public int setList (SizeT x1, CefListValue x2) {

return  this.set_list.set_list(this,  x1,  x2);

}}