package com.xiaojianhx.demo.designpattern.singleton;

import com.xiaojianhx.utils.ThreadUtils;

/**
 * 不同步，恶汉式
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月3日下午7:03:16
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    private Singleton2() {
    }

    public static Singleton2 getInstance() {

        if (instance == null) {
            ThreadUtils.sleep(1000);
            instance = new Singleton2();
        }

        return instance;
    }
}