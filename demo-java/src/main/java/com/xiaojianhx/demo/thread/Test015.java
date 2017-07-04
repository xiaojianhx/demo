package com.xiaojianhx.demo.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test015 {

    public static void main(String[] args) {

        int a = 0;
        TimerTask tt = new TimerTask() {

            int b = a;

            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaa-" + (b++));
            }
        };

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, -10);
        Date runDate = c.getTime();

        Timer t = new Timer();
        t.scheduleAtFixedRate(tt, runDate, 1000);
    }
}