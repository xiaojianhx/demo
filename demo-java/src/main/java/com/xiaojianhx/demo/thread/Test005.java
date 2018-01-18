package com.xiaojianhx.demo.thread;

/**
 * 脏读
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月18日下午11:18:29
 */
public class Test005 {

    public static void main(String[] args) {

        Service005 service = new Service005();
        new Thread(() -> service.setValue("B", "BB"), "aaaa").start();
        ThreadUtils.sleep(100);
        service.getValue();
    }
}

class Service005 {

    private String username = "A";
    private String password = "AA";

    synchronized public void setValue(String username, String password) {
        this.username = username;
        ThreadUtils.sleep(500);
        this.password = password;

        System.out.println(Thread.currentThread().getName() + " set value, username=" + username + ", password=" + password + "");
    }

    public void getValue() {
        System.out.println(Thread.currentThread().getName() + " get value, username=" + username + ", password=" + password + "");
    }
}