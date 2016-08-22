package com.xiaojianhx.demo.spring.aop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceImpl implements Service {

    private static Logger log = LoggerFactory.getLogger(ServiceImpl.class);

    public void method0() {
        log.info("ServiceImpl#method running...");
    }

    public Integer method1() {
        return 1;
    }

    public void method2() {
        System.out.println(1 / 0);
    }
}