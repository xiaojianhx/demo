package com.xiaojianhx.demo.thread_old;

public class Test000 {

    public static void main(String[] args) {

        new Thread(new Test()).run();
    }

    static class Test implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread().getName() + " --> a");
        }
    }
}
