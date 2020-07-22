package com.xiaojianhx.demo.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaojianhx.demo.spring.aop.service.Service;

public class Application {

    public static void main(String[] args) {

        // config();

        annotation();
    }

    public static void config() {

        var ac = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-config.xml");

        var service = (Service) ac.getBean("service");

        System.out.println("=============================方法一=============================");
        service.method0();

        System.out.println("=============================方法二=============================");
        service.method1();

        System.out.println("=============================方法三=============================");
        service.method2();

        ac.close();
    }

    private static void annotation() {

        var ac = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-annotation.xml");

        var service = (Service) ac.getBean("service");

        System.out.println("=============================方法一=============================");
        service.method0();

        System.out.println("=============================方法二=============================");
        service.method1();

        System.out.println("=============================方法三=============================");
        service.method2();

        ac.close();
    }
}