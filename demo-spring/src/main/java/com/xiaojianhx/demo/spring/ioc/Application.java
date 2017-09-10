package com.xiaojianhx.demo.spring.ioc;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaojianhx.demo.spring.ioc.bean.Bean00;
import com.xiaojianhx.demo.spring.ioc.bean.Bean01;
import com.xiaojianhx.demo.spring.ioc.bean.Bean10;
import com.xiaojianhx.demo.spring.ioc.bean.Bean20;
import com.xiaojianhx.demo.spring.ioc.bean.BeanFactory;

public class Application {

    private static Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        createObject();

        createObjectScope();
    }

    private static void createObject() {

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

    private static void createObjectScope() {

        ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        CountDownLatch latch = new CountDownLatch(4);

        GetBean bean00 = new GetBean(latch, ac, "bean30");
        GetBean bean01 = new GetBean(latch, ac, "bean30");

        GetBean bean10 = new GetBean(latch, ac, "bean31");
        GetBean bean11 = new GetBean(latch, ac, "bean31");

        new Thread(bean00).start();
        new Thread(bean01).start();
        new Thread(bean10).start();
        new Thread(bean11).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("单例模式:" + bean00.getBean());
        System.out.println("单例模式:" + bean01.getBean());
        System.out.println("多例模式:" + bean10.getBean());
        System.out.println("多例模式:" + bean11.getBean());
    }

    private static class GetBean implements Runnable {

        private CountDownLatch latch;
        private ConfigurableApplicationContext ac;
        private String beanName;
        private Object bean;

        public GetBean(CountDownLatch latch, ConfigurableApplicationContext ac, String beanName) {
            this.latch = latch;
            this.ac = ac;
            this.beanName = beanName;
        }

        public Object getBean() {
            return bean;
        }

        public void run() {
            bean = ac.getBean(beanName);
            latch.countDown();
        }
    }
}