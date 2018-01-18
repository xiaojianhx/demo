package com.xiaojianhx.demo.thread;

/**
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:18:29
 */
public class Test003 {

    public static void main(String[] args) {

        Service003 service = new Service003();
        new Thread(() -> service.methodA(), "aaaa").start();
        new Thread(() -> service.methodB(), "bbbb").start();
    }
}

class Service003 {

    synchronized public void methodA() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtils.sleep(200);
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public void methodB() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtils.sleep(100);
        System.out.println(Thread.currentThread().getName() + " end");
    }
}