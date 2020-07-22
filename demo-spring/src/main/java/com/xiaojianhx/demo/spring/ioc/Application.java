package com.xiaojianhx.demo.spring.ioc;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiaojianhx.demo.spring.ioc.bean.Bean00;
import com.xiaojianhx.demo.spring.ioc.bean.Bean01;
import com.xiaojianhx.demo.spring.ioc.bean.Bean10;
import com.xiaojianhx.demo.spring.ioc.bean.Bean20;
import com.xiaojianhx.demo.spring.ioc.bean.BeanFactory;

public class Application {

    public static void main(String[] args) {

        createObject();

        createObjectScope();
    }

    private static void createObject() {

        var ac = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        System.out.println("=========================第一种方式创建对象:set方法============================");
        var bean00 = (Bean00) ac.getBean("bean00");
        System.out.println(bean00.getAccount());
        System.out.println(bean00.getPassword());

        System.out.println("=========================第一种方式创建对象:构造器============================");
        var bean01 = (Bean01) ac.getBean("bean01");
        System.out.println(bean01.getAccount());
        System.out.println(bean01.getPassword());

        System.out.println("=========================第二种方式创建对象:静态工厂方法============================");
        var bean10 = (Bean10) ac.getBean("bean10");
        System.out.println(bean10.getAccount());
        System.out.println(bean10.getPassword());

        System.out.println("=========================第三种方式创建对象:工厂方法============================");
        var factory = (BeanFactory) ac.getBean("factory");

        var bean20 = (Bean20) factory.getObject();

        System.out.println(bean20.getAccount());
        System.out.println(bean20.getPassword());

        ac.close();
    }

    private static void createObjectScope() {

        var ac = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        var latch = new CountDownLatch(4);

        var bean00 = new GetBean(latch, ac, "bean30");
        var bean01 = new GetBean(latch, ac, "bean30");

        var bean10 = new GetBean(latch, ac, "bean31");
        var bean11 = new GetBean(latch, ac, "bean31");

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