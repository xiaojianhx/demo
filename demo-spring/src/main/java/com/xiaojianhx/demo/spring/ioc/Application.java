package com.xiaojianhx.demo.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaojianhx.demo.spring.ioc.bean.Bean00;
import com.xiaojianhx.demo.spring.ioc.bean.Bean01;
import com.xiaojianhx.demo.spring.ioc.bean.Bean10;
import com.xiaojianhx.demo.spring.ioc.bean.Bean20;
import com.xiaojianhx.demo.spring.ioc.bean.BeanFactory;

public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        log.info("=========================第一种方式创建对象:set方法============================");
        Bean00 bean00 = (Bean00) ac.getBean("bean00");
        log.info(bean00.getAccount());
        log.info(bean00.getPassword());

        log.info("=========================第一种方式创建对象:构造器============================");
        Bean01 bean01 = (Bean01) ac.getBean("bean01");
        log.info(bean01.getAccount());
        log.info(bean01.getPassword());

        log.info("=========================第二种方式创建对象:静态工厂方法============================");
        Bean10 bean10 = (Bean10) ac.getBean("bean10");
        log.info(bean10.getAccount());
        log.info(bean10.getPassword());

        log.info("=========================第三种方式创建对象:工厂方法============================");
        BeanFactory factory = (BeanFactory) ac.getBean("factory");

        Bean20 bean20 = (Bean20) factory.getObject();

        log.info(bean20.getAccount());
        log.info(bean20.getPassword());

        ac.close();
    }
}