package com.xiaojianhx.demo.thread;

/**
 * synchronized this
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:18:29
 */
public class Test007 {

    public static void main(String[] args) {

        Service007 service = new Service007();
        new Thread(() -> service.foreach(), "aaaa").start();
        new Thread(() -> service.otherMethod(), "bbbb").start();
    }
}

class Service007 {

    public void foreach() {
        synchronized (this) {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " ==> " + i);
            }
        }
    }

    synchronized public void otherMethod() {
        System.out.println(Thread.currentThread().getName() + " ==> otherMethod");
    }
}