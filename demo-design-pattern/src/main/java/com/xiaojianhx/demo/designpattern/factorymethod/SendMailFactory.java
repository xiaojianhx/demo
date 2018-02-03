package com.xiaojianhx.demo.designpattern.factorymethod;

/**
 * 静态方法，扩展性好
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日上午1:03:54
 */
public class SendMailFactory implements Provider {

    public Sender produce() {
        return new MailSender();
    }
}
