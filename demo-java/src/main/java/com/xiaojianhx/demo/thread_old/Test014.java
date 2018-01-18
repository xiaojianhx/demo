package com.xiaojianhx.demo.thread_old;

public class Test014 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method("a")).start();
        new Thread(() -> task.method("b")).start();
    }

    private static class Task {

        private Object lock0 = new Object();
        private Object lock1 = new Object();

        public void method(String username) {

            if ("a".equals(username)) {
                synchronized (lock0) {
                    System.out.println("method0 start " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("method0   end " + Thread.currentThread().getName());

                    synchronized (lock1) {
                        System.out.println("lock0 -> lock1, " + Thread.currentThread().getName());
                    }
                }
            } else {

                synchronized (lock1) {
                    System.out.println("method0 start " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("method0   end " + Thread.currentThread().getName());

                    synchronized (lock0) {
                        System.out.println("lock1 -> lock0, " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
}