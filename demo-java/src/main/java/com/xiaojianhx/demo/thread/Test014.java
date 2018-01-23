package com.xiaojianhx.demo.thread;

/**
 * wait
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月23日下午9:40:42
 */
public class Test014 {

    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {

            try {
                synchronized (lock) {

                    System.out.println("  wait 0 " + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("  wait 1 " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        ThreadUtils.sleep(3000);
        new Thread(() -> {

            try {
                synchronized (lock) {

                    System.out.println("notify 0 " + System.currentTimeMillis());
                    lock.notify();
                    System.out.println("notify 1 " + System.currentTimeMillis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}