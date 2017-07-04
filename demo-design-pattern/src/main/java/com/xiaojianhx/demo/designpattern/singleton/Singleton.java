package com.xiaojianhx.demo.designpattern.singleton;

public class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {

        if (instance != null) {

        } else {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {

        int size = 10;
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                System.out.println(Singleton.getInstance().hashCode());
            }).start();
        }
    }
}