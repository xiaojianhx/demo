package com.xiaojianhx.demo.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaojianhx.demo.spring.aop.service.Service;

public class Application {

    private static Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        // config();

        annotation();
    }

    public static void config() {

        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-config.xml");

        Service service = (Service) ac.getBean("service");

        log.info("=============================方法一=============================");
        service.method0();

        log.info("=============================方法二=============================");
        service.method1();

        log.info("=============================方法三=============================");
        service.method2();

        ac.close();
    }

    private static void annotation() {

        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-annotation.xml");

        Service service = (Service) ac.getBean("service");

        log.info("=============================方法一=============================");
        service.method0();

        log.info("=============================方法二=============================");
        service.method1();

        log.info("=============================方法三=============================");
        service.method2();

        ac.close();

    }
}