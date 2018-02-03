package com.xiaojianhx.demo.designpattern.singleton;

/**
 * 内部静态类，构造方法抛出异常，永远不会创建出对象
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月3日下午7:03:16
 */
public class Singleton3 {

    private static class Lazy {
        private static Singleton3 instance = new Singleton3();
    }

    private Singleton3() {
        // System.out.println(1 / 0);
    }

    public static Singleton3 getInstance() {
        return Lazy.instance;
    }
}