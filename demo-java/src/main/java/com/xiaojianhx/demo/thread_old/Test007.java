package com.xiaojianhx.demo.thread_old;

public class Test007 {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(() -> task.method()).start();
        new Thread(() -> task.method()).start();
    }

    private static class Task {

        public void method() {

            for (int i = 0; i < 20; i++) {
                System.out.println("asynchronized:" + Thread.currentThread().getName() + ", i=" + i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");
            synchronized (this) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(" synchronized:" + Thread.currentThread().getName() + ", i=" + i);
                }
            }
        }
    }
}
