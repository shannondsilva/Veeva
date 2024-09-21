package com.example.utils;

import org.junit.BeforeClass;

public class StaticVariables {
    public static ThreadLocal<String> browserParallel = new ThreadLocal<>();

    public static String getBrowserParallel() {
        System.out.println("-------------------xxxx--------------"+browserParallel.get());
        return browserParallel.get();
//        return System.getProperty("testng.browser");
    }
}
