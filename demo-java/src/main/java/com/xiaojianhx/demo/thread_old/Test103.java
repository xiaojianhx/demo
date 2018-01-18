package com.xiaojianhx.demo.thread_old;

public class Test103 {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {

        Service service = new Service();
        new Thread(() -> service.a()).start();
        new Thread(() -> service.b()).start();
        new Thread(() -> service.c()).start();
    }

    private static class Service {

        synchronized public static void a() {
            System.out.println(Thread.currentThread().getName() + " --> a start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --> a end");
        }

        synchronized public static void b() {
            System.out.println(Thread.currentThread().getName() + " --> b start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --> b end");
        }

        synchronized public void c() {
            System.out.println(Thread.currentThread().getName() + " --> c start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " --> c end");
        }
    }
}
