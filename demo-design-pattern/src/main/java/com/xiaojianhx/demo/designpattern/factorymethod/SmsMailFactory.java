package com.xiaojianhx.demo.designpattern.factorymethod;

/**
 * 静态方法，扩展性好
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日上午1:04:09
 */
public class SmsMailFactory implements Provider {

    public Sender produce() {
        return new SmsSender();
    }
}