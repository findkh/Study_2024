package com.kh.demoapi;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CallStatic {
    public static  ConcurrentHashMap<String, String> callStatus = new ConcurrentHashMap<>();

    public static  ConcurrentHashMap<String, Set<String>> sessions = new ConcurrentHashMap<>();
}
