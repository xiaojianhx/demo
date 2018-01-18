package com.xiaojianhx.demo.thread_old;

/**
 * 线程自己一个对象，不会冲突test0<br/>
 * 线程共享一个对象，会有线程冲突 test1,改进@see {@link Test002}
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月4日下午9:27:30
 */
public class Test001 {

    public static void main(String[] args) {

        test0();
        test1();
    }

    private static void test0() {

        System.out.println("这个没有线程冲突");
        new Thread(() -> new Service().add("a")).start();
        new Thread(() -> new Service().add("b")).start();
    }

    private static void test1() {

        System.out.println("这个会有线程冲突");
        Service service = new Service();
        new Thread(() -> service.add("a")).start();
        new Thread(() -> service.add("b")).start();
    }

    private static class Service {

        private int num = 0;

        public void add(String username) {

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
