package com.xiaojianhx.demo.thread_old;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建只能保存一个元素的list
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月9日下午5:03:21
 */
public class Test102 {

    public static void main(String[] args) {

        Service list = new Service();
        new Thread(() -> {
            new Service1().add(list, "a");
        }).start();
        new Thread(() -> {
            new Service1().add(list, "b");
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.getSize());
        System.out.println(list.get());

    }

    private static class Service1 {

        public Service add(Service list, String data) {

            try {
                synchronized (list) {
                    if (list.getSize() < 1) {
                        Thread.sleep(1000);
                        list.add(data);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return list;
        }
    }

    private static class Service {

        private List list = new ArrayList<>();

        synchronized public void add(String data) {
            list.add(data);
        }

        synchronized public int getSize() {
            return list.size();
        }

        synchronized public List get() {
            return list;
        }
    }
}
