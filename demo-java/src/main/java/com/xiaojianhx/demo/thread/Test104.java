package com.xiaojianhx.demo.thread;

/**
 * 静态class锁，共享
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月9日下午5:51:23
 */
public class Test104 {

    public static void main(String[] args) {
        new Thread(() -> Service.a()).start();
        new Thread(() -> Service.b()).start();
    }

    private static class Service {

        synchronized public static void a() {
            System.out.println(Thread.currentThread().getName() + " --> a start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b();
            System.out.println(Thread.currentThread().getName() + " --> a end");
        }

        synchronized public static void b() {
            System.out.println(Thread.currentThread().getName() + " --> b start");
            a();
            System.out.println(Thread.currentThread().getName() + " --> b end");
        }
    }
}
