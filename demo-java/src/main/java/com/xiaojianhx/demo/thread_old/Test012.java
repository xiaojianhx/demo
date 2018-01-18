package com.xiaojianhx.demo.thread_old;

public class Test012 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method0()).start();
        new Thread(() -> task.method1()).start();
    }

    private static class Task {

        synchronized public void method0() {
            System.out.println("method0 start" + Thread.currentThread().getName());

            boolean flg = true;
            while (flg) {

            }

            System.out.println("method0 end " + Thread.currentThread().getName());
        }

        synchronized public void method1() {
            System.out.println("method1 start" + Thread.currentThread().getName());
            System.out.println("method1 end " + Thread.currentThread().getName());
        }
    }
}
