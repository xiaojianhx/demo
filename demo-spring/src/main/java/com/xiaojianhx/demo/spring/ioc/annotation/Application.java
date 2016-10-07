package com.xiaojianhx.demo.spring.ioc.annotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext-annotation.xml");

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ac.getBean("controller");
            }
        }).start();

        ac.close();
    }
}