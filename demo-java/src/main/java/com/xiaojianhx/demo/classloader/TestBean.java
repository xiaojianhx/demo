package com.xiaojianhx.demo.classloader;

public class TestBean {

    static {
        System.out.println("静态块");
    }

    public TestBean() {
        System.out.println("构造器");
    }
}