package com.xiaojianhx.demo.thread;

/**
 * join
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月23日下午9:40:42
 */
public class Test018 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("main");
    }
}