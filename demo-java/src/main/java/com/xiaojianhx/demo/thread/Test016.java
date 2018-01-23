package com.xiaojianhx.demo.thread;

/**
 * wait
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月23日下午9:40:42
 */
public class Test016 {

    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> new Service016().test1(lock)).start();
        new Thread(() -> new Service016().test2(lock)).start();
    }
}

class Service016 {

    public void test1(Object lock) {

        try {
            synchronized (lock) {

                System.out.println(Thread.currentThread().getName() + " --> " + "begin wait");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " --> " + "  end wait");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2(Object lock) {

        try {
            synchronized (lock) {

                System.out.println(Thread.currentThread().getName() + " --> " + "begin wait");
                lock.notify();
                ThreadUtils.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " --> " + "  end wait");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}