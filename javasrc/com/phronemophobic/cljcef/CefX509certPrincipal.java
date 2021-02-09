package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefX509certPrincipal extends Structure{
public CefX509certPrincipal(){
base.size.setValue(this.size());
}

public static interface GetDisplayNameFunc extends Callback {

CefStringUtf16 get_display_name(CefX509certPrincipal x0); 
}

public static interface GetCommonNameFunc extends Callback {

CefStringUtf16 get_common_name(CefX509certPrincipal x0); 
}

public static interface GetLocalityNameFunc extends Callback {

CefStringUtf16 get_locality_name(CefX509certPrincipal x0); 
}

public static interface GetStateOrProvinceNameFunc extends Callback {

CefStringUtf16 get_state_or_province_name(CefX509certPrincipal x0); 
}

public static interface GetCountryNameFunc extends Callback {

CefStringUtf16 get_country_name(CefX509certPrincipal x0); 
}

public static interface GetStreetAddressesFunc extends Callback {

void get_street_addresses(CefX509certPrincipal x0, Pointer x1); 
}

public static interface GetOrganizationNamesFunc extends Callback {

void get_organization_names(CefX509certPrincipal x0, Pointer x1); 
}

public static interface GetOrganizationUnitNamesFunc extends Callback {

void get_organization_unit_names(CefX509certPrincipal x0, Pointer x1); 
}

public static interface GetDomainComponentsFunc extends Callback {

void get_domain_components(CefX509certPrincipal x0, Pointer x1); 
}

public CefBaseRefCounted base;

public GetDisplayNameFunc get_display_name;

public GetCommonNameFunc get_common_name;

public GetLocalityNameFunc get_locality_name;

public GetStateOrProvinceNameFunc get_state_or_province_name;

public GetCountryNameFunc get_country_name;

public GetStreetAddressesFunc get_street_addresses;

public GetOrganizationNamesFunc get_organization_names;

public GetOrganizationUnitNamesFunc get_organization_unit_names;

public GetDomainComponentsFunc get_domain_components;

protected List getFieldOrder() {
                                            return Arrays.asList("base", "get_display_name", "get_common_name", "get_locality_name", "get_state_or_province_name", "get_country_name", "get_street_addresses", "get_organization_names", "get_organization_unit_names", "get_domain_components");
 }}