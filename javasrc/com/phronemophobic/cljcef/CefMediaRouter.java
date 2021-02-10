// AUTO GENERATED BY gen2.clj!

package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefMediaRouter extends Structure{
public CefMediaRouter(){
base.size.setValue(this.size());

ReferenceCountImpl.track(this.getPointer());

}

public static interface AddObserverFunc extends Callback {

CefRegistration add_observer(CefMediaRouter x0, CefMediaObserver x1); 
}

public static interface GetSourceFunc extends Callback {

CefMediaSource get_source(CefMediaRouter x0, CefStringUtf16 x1); 
}

public static interface NotifyCurrentSinksFunc extends Callback {

void notify_current_sinks(CefMediaRouter x0); 
}

public static interface CreateRouteFunc extends Callback {

void create_route(CefMediaRouter x0, CefMediaSource x1, CefMediaSink x2, CefMediaRouteCreateCallback x3); 
}

public static interface NotifyCurrentRoutesFunc extends Callback {

void notify_current_routes(CefMediaRouter x0); 
}

public CefBaseRefCounted base;

public AddObserverFunc add_observer;

public GetSourceFunc get_source;

public NotifyCurrentSinksFunc notify_current_sinks;

public CreateRouteFunc create_route;

public NotifyCurrentRoutesFunc notify_current_routes;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "add_observer", "get_source", "notify_current_sinks", "create_route", "notify_current_routes");
 }

public CefRegistration addObserver (CefMediaObserver x1) {

return  this.add_observer.add_observer(this,  x1);

}

public CefMediaSource getSource (String x1) {

return  this.get_source.get_source(this, CljCefLib.toCefString(x1));

}

public void notifyCurrentSinks () {

  this.notify_current_sinks.notify_current_sinks(this);

}

public void createRoute (CefMediaSource x1, CefMediaSink x2, CefMediaRouteCreateCallback x3) {

  this.create_route.create_route(this,  x1,  x2,  x3);

}

public void notifyCurrentRoutes () {

  this.notify_current_routes.notify_current_routes(this);

}}