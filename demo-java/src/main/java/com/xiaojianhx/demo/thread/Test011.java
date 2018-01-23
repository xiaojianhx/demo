package com.xiaojianhx.demo.thread;

/**
 * volatile 用synchronized保证原子性
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月20日下午2:51:25
 */
public class Test011 {

    public static void main(String[] args) {

        Service011 service = new Service011();

        for (int i = 0; i < 100; i++) {
            new Thread(service).start();
        }
    }
}

class Service011 implements Runnable {

    private volatile int count = 0;

    synchronized public void run() {

        for (int i = 0; i < 100; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + " ==>" + count);
    }
}