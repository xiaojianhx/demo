package com.xiaojianhx.demo.thread;

/**
 * wait
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月23日下午9:40:42
 */
public class Test015 {

    public static void main(String[] args) {

        Object lock = new Object();

        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                Service015 service = new Service015();
                service.test(lock);
            }).start();
        }
    }
}

class Service015 {

    public void test(Object lock) {

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
}