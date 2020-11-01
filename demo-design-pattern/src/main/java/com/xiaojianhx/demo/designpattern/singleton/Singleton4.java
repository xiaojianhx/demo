package com.xiaojianhx.demo.designpattern.singleton;

/**
 * 不同步，恶汉式
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月3日下午7:03:16
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {

        if (instance == null) {
            init();
        }
        return instance;
    }

    private synchronized static void init() {

        if (instance == null) {
            instance = new Singleton4();
        }
    }

    protected Object clone() throws CloneNotSupportedException {
        return this;
    }
}