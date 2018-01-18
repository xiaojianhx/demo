package com.xiaojianhx.demo.thread;

/**
 * 全局变量会有冲突
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:00:37
 */
public class Test001 {

    public static void main(String[] args) {

        Service001 service = new Service001();
        new Thread(() -> service.test(0), "aaaa").start();
        new Thread(() -> service.test(1), "bbbb").start();
    }
}

class Service001 {

    int num = 0;

    public void test(int flg) {

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