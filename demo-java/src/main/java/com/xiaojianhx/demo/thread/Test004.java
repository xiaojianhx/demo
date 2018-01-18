package com.xiaojianhx.demo.thread;

/**
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:18:29
 */
public class Test004 {

    public static void main(String[] args) {

        Service004 service = new Service004();
        new Thread(() -> service.methodA(), "aaaa").start();
        new Thread(() -> service.methodB(), "bbbb").start();
    }
}

class Service004 {

    synchronized public void methodA() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtils.sleep(200);
        System.out.println(Thread.currentThread().getName() + " end");
    }

    synchronized public void methodB() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtils.sleep(100);
        System.out.println(Thread.currentThread().getName() + " end");
    }
}