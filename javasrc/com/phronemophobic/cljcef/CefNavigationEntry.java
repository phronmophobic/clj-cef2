// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefNavigationEntry extends Structure{
public CefNavigationEntry(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface IsValidFunc extends Callback {

int is_valid(CefNavigationEntry x0); 
}

public static interface GetUrlFunc extends Callback {

CefStringUtf16 get_url(CefNavigationEntry x0); 
}

public static interface GetDisplayUrlFunc extends Callback {

CefStringUtf16 get_display_url(CefNavigationEntry x0); 
}

public static interface GetOriginalUrlFunc extends Callback {

CefStringUtf16 get_original_url(CefNavigationEntry x0); 
}

public static interface GetTitleFunc extends Callback {

CefStringUtf16 get_title(CefNavigationEntry x0); 
}

public static interface GetTransitionTypeFunc extends Callback {

int get_transition_type(CefNavigationEntry x0); 
}

public static interface HasPostDataFunc extends Callback {

int has_post_data(CefNavigationEntry x0); 
}

public static interface GetCompletionTimeFunc extends Callback {

CefTime get_completion_time(CefNavigationEntry x0); 
}

public static interface GetHttpStatusCodeFunc extends Callback {

int get_http_status_code(CefNavigationEntry x0); 
}

public static interface GetSslstatusFunc extends Callback {

CefSslstatus get_sslstatus(CefNavigationEntry x0); 
}

public CefBaseRefCounted base;

public IsValidFunc is_valid;

public GetUrlFunc get_url;

public GetDisplayUrlFunc get_display_url;

public GetOriginalUrlFunc get_original_url;

public GetTitleFunc get_title;

public GetTransitionTypeFunc get_transition_type;

public HasPostDataFunc has_post_data;

public GetCompletionTimeFunc get_completion_time;

public GetHttpStatusCodeFunc get_http_status_code;

public GetSslstatusFunc get_sslstatus;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "is_valid", "get_url", "get_display_url", "get_original_url", "get_title", "get_transition_type", "has_post_data", "get_completion_time", "get_http_status_code", "get_sslstatus");
 }

public int isValid () {

return  this.is_valid.is_valid(this);

}

public CefStringUtf16 getUrl () {

return  this.get_url.get_url(this);

}

public CefStringUtf16 getDisplayUrl () {

return  this.get_display_url.get_display_url(this);

}

public CefStringUtf16 getOriginalUrl () {

return  this.get_original_url.get_original_url(this);

}

public CefStringUtf16 getTitle () {

return  this.get_title.get_title(this);

}

public int getTransitionType () {

return  this.get_transition_type.get_transition_type(this);

}

public int hasPostData () {

return  this.has_post_data.has_post_data(this);

}

public CefTime getCompletionTime () {

return  this.get_completion_time.get_completion_time(this);

}

public int getHttpStatusCode () {

return  this.get_http_status_code.get_http_status_code(this);

}

public CefSslstatus getSslstatus () {

return  this.get_sslstatus.get_sslstatus(this);

}}