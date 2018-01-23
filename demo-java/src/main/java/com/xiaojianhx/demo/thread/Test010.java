package com.xiaojianhx.demo.thread;

/**
 * volatile 非原子性
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月20日下午2:51:25
 */
public class Test010 {

    public static void main(String[] args) {

        Service010 service = new Service010();

        for (int i = 0; i < 100; i++) {
            new Thread(service).start();
        }
    }
}

class Service010 implements Runnable {

    private volatile int count = 0;

    public void run() {

        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " ==>" + count);
    }
}