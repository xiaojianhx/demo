package com.xiaojianhx.demo.designpattern.singleton;

import com.xiaojianhx.common.utils.ThreadUtils;

/**
 * 不同步，懒汉式
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月3日下午7:03:16
 */
public class Singleton1 {

    private static Singleton1 instance;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {

        if (instance == null) {
            ThreadUtils.sleep(1000);
            instance = new Singleton1();
        }

        return instance;
    }
}