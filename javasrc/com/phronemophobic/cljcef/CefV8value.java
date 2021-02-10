// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefV8value extends Structure{
public CefV8value(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface IsValidFunc extends Callback {

int is_valid(CefV8value x0); 
}

public static interface IsUndefinedFunc extends Callback {

int is_undefined(CefV8value x0); 
}

public static interface IsNullFunc extends Callback {

int is_null(CefV8value x0); 
}

public static interface IsBoolFunc extends Callback {

int is_bool(CefV8value x0); 
}

public static interface IsIntFunc extends Callback {

int is_int(CefV8value x0); 
}

public static interface IsUintFunc extends Callback {

int is_uint(CefV8value x0); 
}

public static interface IsDoubleFunc extends Callback {

int is_double(CefV8value x0); 
}

public static interface IsDateFunc extends Callback {

int is_date(CefV8value x0); 
}

public static interface IsStringFunc extends Callback {

int is_string(CefV8value x0); 
}

public static interface IsObjectFunc extends Callback {

int is_object(CefV8value x0); 
}

public static interface IsArrayFunc extends Callback {

int is_array(CefV8value x0); 
}

public static interface IsArrayBufferFunc extends Callback {

int is_array_buffer(CefV8value x0); 
}

public static interface IsFunctionFunc extends Callback {

int is_function(CefV8value x0); 
}

public static interface IsSameFunc extends Callback {

int is_same(CefV8value x0, CefV8value x1); 
}

public static interface GetBoolValueFunc extends Callback {

int get_bool_value(CefV8value x0); 
}

public static interface GetIntValueFunc extends Callback {

int get_int_value(CefV8value x0); 
}

public static interface GetUintValueFunc extends Callback {

int get_uint_value(CefV8value x0); 
}

public static interface GetDoubleValueFunc extends Callback {

double get_double_value(CefV8value x0); 
}

public static interface GetDateValueFunc extends Callback {

CefTime get_date_value(CefV8value x0); 
}

public static interface GetStringValueFunc extends Callback {

CefStringUtf16 get_string_value(CefV8value x0); 
}

public static interface IsUserCreatedFunc extends Callback {

int is_user_created(CefV8value x0); 
}

public static interface HasExceptionFunc extends Callback {

int has_exception(CefV8value x0); 
}

public static interface GetExceptionFunc extends Callback {

CefV8exception get_exception(CefV8value x0); 
}

public static interface ClearExceptionFunc extends Callback {

int clear_exception(CefV8value x0); 
}

public static interface WillRethrowExceptionsFunc extends Callback {

int will_rethrow_exceptions(CefV8value x0); 
}

public static interface SetRethrowExceptionsFunc extends Callback {

int set_rethrow_exceptions(CefV8value x0, int x1); 
}

public static interface HasValueBykeyFunc extends Callback {

int has_value_bykey(CefV8value x0, CefStringUtf16 x1); 
}

public static interface HasValueByindexFunc extends Callback {

int has_value_byindex(CefV8value x0, int x1); 
}

public static interface DeleteValueBykeyFunc extends Callback {

int delete_value_bykey(CefV8value x0, CefStringUtf16 x1); 
}

public static interface DeleteValueByindexFunc extends Callback {

int delete_value_byindex(CefV8value x0, int x1); 
}

public static interface GetValueBykeyFunc extends Callback {

CefV8value get_value_bykey(CefV8value x0, CefStringUtf16 x1); 
}

public static interface GetValueByindexFunc extends Callback {

CefV8value get_value_byindex(CefV8value x0, int x1); 
}

public static interface SetValueBykeyFunc extends Callback {

int set_value_bykey(CefV8value x0, CefStringUtf16 x1, CefV8value x2, int x3); 
}

public static interface SetValueByindexFunc extends Callback {

int set_value_byindex(CefV8value x0, int x1, CefV8value x2); 
}

public static interface SetValueByaccessorFunc extends Callback {

int set_value_byaccessor(CefV8value x0, CefStringUtf16 x1, int x2, int x3); 
}

public static interface GetKeysFunc extends Callback {

int get_keys(CefV8value x0, Pointer x1); 
}

public static interface SetUserDataFunc extends Callback {

int set_user_data(CefV8value x0, CefBaseRefCounted x1); 
}

public static interface GetUserDataFunc extends Callback {

CefBaseRefCounted get_user_data(CefV8value x0); 
}

public static interface GetExternallyAllocatedMemoryFunc extends Callback {

int get_externally_allocated_memory(CefV8value x0); 
}

public static interface AdjustExternallyAllocatedMemoryFunc extends Callback {

int adjust_externally_allocated_memory(CefV8value x0, int x1); 
}

public static interface GetArrayLengthFunc extends Callback {

int get_array_length(CefV8value x0); 
}

public static interface GetArrayBufferReleaseCallbackFunc extends Callback {

CefV8arrayBufferReleaseCallback get_array_buffer_release_callback(CefV8value x0); 
}

public static interface NeuterArrayBufferFunc extends Callback {

int neuter_array_buffer(CefV8value x0); 
}

public static interface GetFunctionNameFunc extends Callback {

CefStringUtf16 get_function_name(CefV8value x0); 
}

public static interface GetFunctionHandlerFunc extends Callback {

CefV8handler get_function_handler(CefV8value x0); 
}

public static interface ExecuteFunctionFunc extends Callback {

CefV8value execute_function(CefV8value x0, CefV8value x1, SizeT x2, Pointer x3); 
}

public static interface ExecuteFunctionWithContextFunc extends Callback {

CefV8value execute_function_with_context(CefV8value x0, CefV8context x1, CefV8value x2, SizeT x3, Pointer x4); 
}

public CefBaseRefCounted base;

public IsValidFunc is_valid;

public IsUndefinedFunc is_undefined;

public IsNullFunc is_null;

public IsBoolFunc is_bool;

public IsIntFunc is_int;

public IsUintFunc is_uint;

public IsDoubleFunc is_double;

public IsDateFunc is_date;

public IsStringFunc is_string;

public IsObjectFunc is_object;

public IsArrayFunc is_array;

public IsArrayBufferFunc is_array_buffer;

public IsFunctionFunc is_function;

public IsSameFunc is_same;

public GetBoolValueFunc get_bool_value;

public GetIntValueFunc get_int_value;

public GetUintValueFunc get_uint_value;

public GetDoubleValueFunc get_double_value;

public GetDateValueFunc get_date_value;

public GetStringValueFunc get_string_value;

public IsUserCreatedFunc is_user_created;

public HasExceptionFunc has_exception;

public GetExceptionFunc get_exception;

public ClearExceptionFunc clear_exception;

public WillRethrowExceptionsFunc will_rethrow_exceptions;

public SetRethrowExceptionsFunc set_rethrow_exceptions;

public HasValueBykeyFunc has_value_bykey;

public HasValueByindexFunc has_value_byindex;

public DeleteValueBykeyFunc delete_value_bykey;

public DeleteValueByindexFunc delete_value_byindex;

public GetValueBykeyFunc get_value_bykey;

public GetValueByindexFunc get_value_byindex;

public SetValueBykeyFunc set_value_bykey;

public SetValueByindexFunc set_value_byindex;

public SetValueByaccessorFunc set_value_byaccessor;

public GetKeysFunc get_keys;

public SetUserDataFunc set_user_data;

public GetUserDataFunc get_user_data;

public GetExternallyAllocatedMemoryFunc get_externally_allocated_memory;

public AdjustExternallyAllocatedMemoryFunc adjust_externally_allocated_memory;

public GetArrayLengthFunc get_array_length;

public GetArrayBufferReleaseCallbackFunc get_array_buffer_release_callback;

public NeuterArrayBufferFunc neuter_array_buffer;

public GetFunctionNameFunc get_function_name;

public GetFunctionHandlerFunc get_function_handler;

public ExecuteFunctionFunc execute_function;

public ExecuteFunctionWithContextFunc execute_function_with_context;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "is_valid", "is_undefined", "is_null", "is_bool", "is_int", "is_uint", "is_double", "is_date", "is_string", "is_object", "is_array", "is_array_buffer", "is_function", "is_same", "get_bool_value", "get_int_value", "get_uint_value", "get_double_value", "get_date_value", "get_string_value", "is_user_created", "has_exception", "get_exception", "clear_exception", "will_rethrow_exceptions", "set_rethrow_exceptions", "has_value_bykey", "has_value_byindex", "delete_value_bykey", "delete_value_byindex", "get_value_bykey", "get_value_byindex", "set_value_bykey", "set_value_byindex", "set_value_byaccessor", "get_keys", "set_user_data", "get_user_data", "get_externally_allocated_memory", "adjust_externally_allocated_memory", "get_array_length", "get_array_buffer_release_callback", "neuter_array_buffer", "get_function_name", "get_function_handler", "execute_function", "execute_function_with_context");
 }

public int isValid () {

return  this.is_valid.is_valid(this);

}

public int isUndefined () {

return  this.is_undefined.is_undefined(this);

}

public int isNull () {

return  this.is_null.is_null(this);

}

public int isBool () {

return  this.is_bool.is_bool(this);

}

public int isInt () {

return  this.is_int.is_int(this);

}

public int isUint () {

return  this.is_uint.is_uint(this);

}

public int isDouble () {

return  this.is_double.is_double(this);

}

public int isDate () {

return  this.is_date.is_date(this);

}

public int isString () {

return  this.is_string.is_string(this);

}

public int isObject () {

return  this.is_object.is_object(this);

}

public int isArray () {

return  this.is_array.is_array(this);

}

public int isArrayBuffer () {

return  this.is_array_buffer.is_array_buffer(this);

}

public int isFunction () {

return  this.is_function.is_function(this);

}

public int isSame (CefV8value x1) {

return  this.is_same.is_same(this,  x1);

}

public int getBoolValue () {

return  this.get_bool_value.get_bool_value(this);

}

public int getIntValue () {

return  this.get_int_value.get_int_value(this);

}

public int getUintValue () {

return  this.get_uint_value.get_uint_value(this);

}

public double getDoubleValue () {

return  this.get_double_value.get_double_value(this);

}

public CefTime getDateValue () {

return  this.get_date_value.get_date_value(this);

}

public CefStringUtf16 getStringValue () {

return  this.get_string_value.get_string_value(this);

}

public int isUserCreated () {

return  this.is_user_created.is_user_created(this);

}

public int hasException () {

return  this.has_exception.has_exception(this);

}

public CefV8exception getException () {

return  this.get_exception.get_exception(this);

}

public int clearException () {

return  this.clear_exception.clear_exception(this);

}

public int willRethrowExceptions () {

return  this.will_rethrow_exceptions.will_rethrow_exceptions(this);

}

public int setRethrowExceptions (int x1) {

return  this.set_rethrow_exceptions.set_rethrow_exceptions(this,  x1);

}

public int hasValueBykey (String x1) {

return  this.has_value_bykey.has_value_bykey(this, CljCefLib.toCefString(x1));

}

public int hasValueByindex (int x1) {

return  this.has_value_byindex.has_value_byindex(this,  x1);

}

public int deleteValueBykey (String x1) {

return  this.delete_value_bykey.delete_value_bykey(this, CljCefLib.toCefString(x1));

}

public int deleteValueByindex (int x1) {

return  this.delete_value_byindex.delete_value_byindex(this,  x1);

}

public CefV8value getValueBykey (String x1) {

return  this.get_value_bykey.get_value_bykey(this, CljCefLib.toCefString(x1));

}

public CefV8value getValueByindex (int x1) {

return  this.get_value_byindex.get_value_byindex(this,  x1);

}

public int setValueBykey (String x1, CefV8value x2, int x3) {

return  this.set_value_bykey.set_value_bykey(this, CljCefLib.toCefString(x1),  x2,  x3);

}

public int setValueByindex (int x1, CefV8value x2) {

return  this.set_value_byindex.set_value_byindex(this,  x1,  x2);

}

public int setValueByaccessor (String x1, int x2, int x3) {

return  this.set_value_byaccessor.set_value_byaccessor(this, CljCefLib.toCefString(x1),  x2,  x3);

}

public int getKeys (Pointer x1) {

return  this.get_keys.get_keys(this,  x1);

}

public int setUserData (CefBaseRefCounted x1) {

return  this.set_user_data.set_user_data(this,  x1);

}

public CefBaseRefCounted getUserData () {

return  this.get_user_data.get_user_data(this);

}

public int getExternallyAllocatedMemory () {

return  this.get_externally_allocated_memory.get_externally_allocated_memory(this);

}

public int adjustExternallyAllocatedMemory (int x1) {

return  this.adjust_externally_allocated_memory.adjust_externally_allocated_memory(this,  x1);

}

public int getArrayLength () {

return  this.get_array_length.get_array_length(this);

}

public CefV8arrayBufferReleaseCallback getArrayBufferReleaseCallback () {

return  this.get_array_buffer_release_callback.get_array_buffer_release_callback(this);

}

public int neuterArrayBuffer () {

return  this.neuter_array_buffer.neuter_array_buffer(this);

}

public CefStringUtf16 getFunctionName () {

return  this.get_function_name.get_function_name(this);

}

public CefV8handler getFunctionHandler () {

return  this.get_function_handler.get_function_handler(this);

}

public CefV8value executeFunction (CefV8value x1, SizeT x2, Pointer x3) {

return  this.execute_function.execute_function(this,  x1,  x2,  x3);

}

public CefV8value executeFunctionWithContext (CefV8context x1, CefV8value x2, SizeT x3, Pointer x4) {

return  this.execute_function_with_context.execute_function_with_context(this,  x1,  x2,  x3,  x4);

}}