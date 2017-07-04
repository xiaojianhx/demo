package com.xiaojianhx.demo.thread;

public class Test011 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method("A")).start();
        new Thread(() -> task.method("B")).start();
    }

    private static class Task {

        public void method(Object obj) {

            try {
                synchronized (obj) {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
