package com.xiaojianhx.demo.thread_old;

public class Test003 {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {

        Service service = new Service();
        new Thread(new ThreadA(service)).start();
        new Thread(new ThreadB(service)).start();
    }

    private static class ThreadA implements Runnable {

        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        public void run() {
            service.method0("a");
        }
    }

    private static class ThreadB implements Runnable {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        public void run() {
            service.method1("b");
        }
    }

    private static class Service {

        synchronized public void method0(String username) {

            try {

                System.out.println("begin method0 threadName=" + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("end endTime=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void method1(String username) {

            try {

                System.out.println("begin method1 threadName=" + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("end endTime=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
