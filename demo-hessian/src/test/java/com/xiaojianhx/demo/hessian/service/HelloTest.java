package com.xiaojianhx.demo.hessian.service;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.xiaojianhx.demo.hessian.service.Hello;

public class HelloTest {

    public static void main(String[] args) {
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            Hello hello = (Hello) factory.create(Hello.class, "http://localhost:8080/demo/hessian/hello");
            System.out.println(hello.say("aaaaaaaa"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}