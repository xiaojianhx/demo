package com.xiaojianhx.demo.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月6日上午12:50:06
 */
public class Test025 {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + " -> " + new Date());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, -2);
        Timer t = new Timer(false);
        TimerTask a = new TimerTask() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " -> " + new Date());
            }
        };
        t.schedule(a, c.getTime());
    }
}
