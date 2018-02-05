package com.xiaojianhx.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 生产者消费者(多对多)
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月6日上午12:06:11
 */
public class Test024 {

    public static void main(String[] args) {

        Service024 service = new Service024();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    service.get();
                }
            }).start();
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    service.set();
                }
            }).start();
        }
    }
}

class Service024 {

    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {

        try {
            lock.lock();
            while (hasValue) {
                System.out.println(Thread.currentThread().getName() + " -> ------------------------有可能连续 ");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " -> ------------------------");
            hasValue = true;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {

        try {
            lock.lock();
            while (!hasValue) {
                System.out.println(Thread.currentThread().getName() + " -> ++++++++++++++++++++++++有可能连续 ");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " -> ++++++++++++++++++++++++");
            hasValue = false;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}