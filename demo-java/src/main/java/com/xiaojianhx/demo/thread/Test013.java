package com.xiaojianhx.demo.thread;

public class Test013 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method0()).start();
        new Thread(() -> task.method1()).start();
    }

    private static class Task {

        private Object obj = new Object();

        public void method0() {

            synchronized (obj) {
                System.out.println("method0 start " + Thread.currentThread().getName());
                boolean flg = true;
                while (flg) {

                }
                System.out.println("method0   end " + Thread.currentThread().getName());
            }
        }

        public void method1() {

            synchronized (this) {
                System.out.println("method1 start " + Thread.currentThread().getName());
                System.out.println("method1   end " + Thread.currentThread().getName());
            }
        }
    }
}
