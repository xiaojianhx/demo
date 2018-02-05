package com.xiaojianhx.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 生产者消费者(一对一)
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月5日下午11:41:35
 */
public class Test022 {

    public static void main(String[] args) {

        Service022 service = new Service022();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                service.get();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                service.set();
            }
        }).start();
    }
}

class Service022 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {

        try {
            lock.lock();
            while (hasValue) {
                System.out.println("set");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " -> ------------------------");
            hasValue = true;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("set finally");
            lock.unlock();
        }
    }

    public void get() {

        try {
            lock.lock();
            while (!hasValue) {
                System.out.println("get");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " -> ++++++++++++++++++++++++");
            hasValue = false;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("get finally");
            lock.unlock();
        }
    }
}