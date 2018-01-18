package com.xiaojianhx.demo.thread;

public interface ThreadUtils {

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
