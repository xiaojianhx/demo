package com.xiaojianhx.demo.designpattern.proxy;

public class Source implements Sourceable {

    public void method() {
        System.out.println("the original method!");
    }
}