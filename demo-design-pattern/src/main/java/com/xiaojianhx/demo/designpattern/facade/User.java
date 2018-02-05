package com.xiaojianhx.demo.designpattern.facade;

public class User {

    public static void main(String[] args) {
        Computer c = new Computer();
        c.startup();
        c.shutdown();
    }
}