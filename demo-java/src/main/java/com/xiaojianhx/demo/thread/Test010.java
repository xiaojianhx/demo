package com.xiaojianhx.demo.thread;

public class Test010 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method("A")).start();
        new Thread(() -> task.method("A")).start();
        new Thread(() -> task.method("B")).start();
    }

    private static class Task {

        public void method(String str) {

            try {
                synchronized (str) {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
