package com.xiaojianhx.demo.designpattern.observer;

public class Client implements Observer {

    private String name;

    public Client(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " 收到推送消息： " + message);
    }
}