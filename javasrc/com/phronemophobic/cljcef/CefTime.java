package com.phronemophobic.cljcef;

import com.phronemophobic.cljcef.*;

import com.sun.jna.Structure;

import com.sun.jna.Callback;

import com.sun.jna.Pointer;

import java.util.List;

import java.util.Arrays;

public class CefTime extends Structure{




public int year;

public int month;

public int day_of_week;

public int day_of_month;

public int hour;

public int minute;

public int second;

public int millisecond;

protected List getFieldOrder() {
                                            return Arrays.asList("year", "month", "day_of_week", "day_of_month", "hour", "minute", "second", "millisecond");
 }}