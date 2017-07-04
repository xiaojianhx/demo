package com.xiaojianhx.demo.thread;

public class Test002 {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {

        System.out.println("加锁就不会有冲突了，这个没有线程冲突");
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
            service.add("a");
        }
    }

    private static class ThreadB implements Runnable {

        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        public void run() {
            service.add("b");
        }
    }

    private static class Service {

        private int num = 0;

        synchronized public void add(String username) {

            try {
                if ("a".equals(username)) {
                    num = 100;
                    System.out.println("a set over");
                    Thread.sleep(1000);
                } else {
                    num = 200;
                    System.out.println("b set over");
                }

                System.out.println(username + " num=" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
