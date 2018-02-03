package com.xiaojianhx.demo.designpattern.factorymethod;

/**
 * 普通方法模式
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日上午12:57:19
 */
public class SendFactoryCommon {

    public Sender produce(String type) {

        if ("mail".equals(type)) {
            return new MailSender();
        }

        if ("sms".equals(type)) {
            return new SmsSender();
        }

        return null;
    }
}