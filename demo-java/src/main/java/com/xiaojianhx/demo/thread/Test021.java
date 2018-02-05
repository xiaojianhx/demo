package com.xiaojianhx.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月5日下午10:43:40
 */
public class Test021 {

    public static void main(String[] args) {

        Service021 service = new Service021();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> service.test()).start();
        }
    }
}

class Service021 {

    private Lock lock = new ReentrantLock();

    public void test() {

        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "," + i);
        }

        lock.unlock();
    }
}