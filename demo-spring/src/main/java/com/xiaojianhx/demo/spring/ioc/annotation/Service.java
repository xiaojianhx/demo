package com.xiaojianhx.demo.spring.ioc.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class Service {

    public Service() {
        System.out.println("创建一个" + toString());
    }
}