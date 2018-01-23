package com.xiaojianhx.demo.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 通信
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月20日下午2:51:25
 */
public class Test013 {

    public static void main(String[] args) {

        Service013 service = new Service013();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                service.add();
                System.out.println("添加了第" + i + "个元素");
                ThreadUtils.sleep(2000);
            }
        }).start();

        new Thread(() -> {

            try {
                while (true) {
                    if (service.size() == 5) {
                        System.out.println("==5，要退出了");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class Service013 {

    private List<String> list = new ArrayList<>();

    public void add() {
        list.add("haha");
    }

    public int size() {
        return list.size();
    }
}