package com.xiaojianhx.demo.spring.aop.service;

public class ServiceImpl implements Service {

    public void method0() {
        System.out.println("ServiceImpl#method running...");
    }

    public Integer method1() {
        return 1;
    }

    public void method2() {
        System.out.println(1 / 0);
    }
}