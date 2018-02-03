package com.xiaojianhx.demo.designpattern.factorymethod;

public class SmsSender implements Sender {

    public void send() {
        System.out.println("sms");
    }
}