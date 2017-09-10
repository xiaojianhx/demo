package com.xiaojianhx.demo.spring.aop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceImpl implements Service {

    private Logger log = LogManager.getLogger(getClass());

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