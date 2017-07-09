package com.xiaojianhx.demo.thread;

public class Test101 {

    public static void main(String[] args) {

        System.out.println("==== 局部变量，不会有线程冲突 ====");
        Service service = new Service();
        new Thread(() -> service.add("a")).start();
        new Thread(() -> service.add("b")).start();
    }

    private static class Service {

        public void add(String username) {

            int num = 0;
            try {
                if ("a".equals(username)) {
                    num = 100;
                    System.out.println("a set over");
                    Thread.sleep(1000);
                } else {
                    num = 200;
                    System.out.println("b set over");
                }

                System.out.println(Thread.currentThread().getName() + " --> " + username + " num=" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
