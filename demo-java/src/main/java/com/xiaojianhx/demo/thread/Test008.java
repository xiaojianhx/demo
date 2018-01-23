package com.xiaojianhx.demo.thread;

/**
 * 死锁
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:18:29
 */
public class Test008 {

    public static void main(String[] args) {

        Service008 service = new Service008();
        new Thread(() -> service.run("a"), "aaaa").start();
        new Thread(() -> service.run("b"), "bbbb").start();
    }
}

class Service008 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void run(String username) {

        if ("a".equals(username)) {

            synchronized (lock1) {

                System.out.println(Thread.currentThread().getName() + " ==> " + username);

                ThreadUtils.sleep(100);

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " ==> " + "lock1 -> lock2");
                }
            }
        }

        if ("b".equals(username)) {

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " ==> " + username);
                ThreadUtils.sleep(100);

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " ==> " + "lock2 -> lock1");
                }
            }
        }
    }
}