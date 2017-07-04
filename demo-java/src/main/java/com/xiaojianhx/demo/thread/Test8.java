package com.xiaojianhx.demo.thread;

public class Test8 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method0()).start();
        new Thread(() -> task.method1()).start();
    }

    private static class Task {

        public void method0() {

            synchronized (this) {
                System.out.println("A bin " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A end " + Thread.currentThread().getName());
            }
        }

        public void method1() {

            synchronized (this) {
                System.out.println("B bin " + Thread.currentThread().getName());
                System.out.println("B end " + Thread.currentThread().getName());
            }
        }
    }
}
