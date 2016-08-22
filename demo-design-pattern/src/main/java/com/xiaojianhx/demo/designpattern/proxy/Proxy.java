package com.xiaojianhx.demo.designpattern.proxy;

public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {
        super();
        this.source = new Source();
    }

    public void method() {
        before();
        source.method();
        atfer();
    }

    private void before() {
        System.out.println("before proxy!");
    }

    private void atfer() {
        System.out.println("after proxy!");
    }

}