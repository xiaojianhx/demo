package com.xiaojianhx.demo.designpattern.factorymethod;

/**
 * 多个方法模式
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日上午12:57:19
 */
public class SendFactoryMore {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }
}