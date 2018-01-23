package com.xiaojianhx.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月20日下午2:51:25
 */
public class Test012 {

    public static void main(String[] args) {

        Service012 service = new Service012();

        for (int i = 0; i < 5; i++) {
            new Thread(service).start();
        }
    }
}

class Service012 implements Runnable {

    private static AtomicInteger count = new AtomicInteger(0);

    public void run() {

        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName() + " ==>" + count.addAndGet(1));
            count.addAndGet(1);
        }
    }
}