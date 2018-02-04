package com.xiaojianhx.demo.designpattern.adapter;

public class Adapter extends Source implements Targetable {

    public void method2() {
        System.out.println("interface method");
    }

    public static void main(String[] args) {

        Targetable t = new Adapter();
        t.method1();
        t.method2();
    }
}