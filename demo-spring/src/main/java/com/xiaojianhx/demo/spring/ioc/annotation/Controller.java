package com.xiaojianhx.demo.spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class Controller {

    @Autowired
    private Service service;

    public Controller() {
        System.out.println("创建一个" + toString());
    }

    public Service getService() {
        return service;
    }
}