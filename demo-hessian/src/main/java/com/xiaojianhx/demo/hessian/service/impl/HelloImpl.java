package com.xiaojianhx.demo.hessian.service.impl;

import com.xiaojianhx.demo.hessian.service.Hello;

public class HelloImpl implements Hello {

    public String say(String name) {
        System.out.println(1 / 0);
        return "hello " + name;
    }
}