package com.xiaojianhx.demo.designpattern.factorymethod;

public class MailSender implements Sender {

    public void send() {
        System.out.println("mail");
    }
}