package com.xiaojianhx.demo.thread;

public class Test006 {

    public static void main(String[] args) {

        Service service = new Service();
        new Thread(() -> service.method()).start();
        new Thread(() -> service.method()).start();
    }

    private static class Service {

        synchronized public void method() {

            if (Thread.currentThread().getName().equals("Thread-0")) {

                int i = 1;
                while (i == 1) {

                    double s = Math.random();
                    if (("" + s).substring(0, 8).equals("0.123456")) {
                        System.out.println(Thread.currentThread().getName() + " error: d=" + s);
                        Integer.parseInt("a");
                    }
                }
            } else {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
