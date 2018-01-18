package com.xiaojianhx.demo.thread;

/**
 * 全局变量会有冲突,加上synchronized就不冲突了
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:00:37
 */
public class Test002 {

    public static void main(String[] args) {

        Service002 service = new Service002();
        new Thread(() -> service.test(0), "aaaa").start();
        new Thread(() -> service.test(1), "bbbb").start();
    }
}

class Service002 {

    private int num = 0;

    synchronized public void test(int flg) {

        if (flg == 0) {
            num = 100;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
        }

        System.out.println(Thread.currentThread().getName() + "," + num);
    }
}