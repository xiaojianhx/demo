package com.xiaojianhx.demo.classloader;

import java.util.Properties;

/**
 * Application
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2021-03-01 15:08:02 init ---- xiaojianhx
 */
public class Application {

    public static void main(String[] args) {

        // property();
        // classloader();
        try {
            ClassLoader loader = Application.class.getClassLoader();

            String clazz = Bean.class.getClass().getName();

            // 使用ClassLoader.loadClass()来加载类，不会执行初始化块
            // loader.loadClass(clazz);

            // 使用Class.forName()来加载类，默认会执行初始化块
            // Class.forName(clazz);

            // 使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
            Class.forName(clazz, true, loader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void property() {

        Properties p = System.getProperties();

        for (Object key : p.keySet()) {
            System.out.println(key + " = " + p.getProperty(key.toString()));
        }
    }

    public static void classloader() {
        var app = new Application();
        Class c = app.getClass();
        ClassLoader loader = c.getClassLoader();
        System.out.println(loader.getParent().getParent());
        System.out.println(loader.getParent());
        System.out.println(loader);
    }
}

class Bean {

    static {
        System.out.println("静态块");
    }

    public Bean() {
        System.out.println("构造器");
    }
}