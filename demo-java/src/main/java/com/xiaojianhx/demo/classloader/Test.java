package com.xiaojianhx.demo.classloader;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        testNew();
        testClassForName();
        testClassForNameNewInstance();
        testClassLoader();

        new HashMap<>();
    }

    private static void testNew() {

        new TestBean();
    }

    private static void testClassForName() {

        try {
            Class.forName(TestBean.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testClassForNameNewInstance() {

        try {
            Class.forName(TestBean.class.getName()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testClassLoader() {
        try {
            Class.forName(TestBean.class.getName(), true, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}