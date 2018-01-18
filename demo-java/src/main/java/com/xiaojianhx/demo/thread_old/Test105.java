package com.xiaojianhx.demo.thread_old;

public class Test105 {

    public static void main(String[] args) {

        Service service = new Service();

        Object obj = new Object();
        new Thread(() -> service.a(obj)).start();
        new Thread(() -> service.a(obj)).start();
    }

    private static class Service {

        public void a(Object obj) {
            synchronized (obj) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }
}
