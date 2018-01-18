package com.xiaojianhx.demo.thread_old;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test016 {

    private static Format f = new SimpleDateFormat("yyyy-MM-dd");
    private static String[] arr = { "2017-01-01", "2017-01-02", "2017-01-03", "2017-01-04", "2017-01-05", "2017-01-06", "2017-01-07", "2017-01-08",
            "2017-01-09", "2017-01-10", "2017-01-11" };

    public static void main(String[] args) {

        for (int i = 0; i < arr.length; i++) {
            new Thread(new Inner(f, arr[i])).start();
        }
    }

    private static class Inner implements Runnable {

        private Format f;
        private String str;

        public Inner(Format f, String str) {
            this.f = f;
            this.str = str;
        }

        public void run() {

            try {
                Date d = (Date) f.parseObject(str);
                String s = f.format(d);

                if (!str.equals(s)) {
                    System.out.println("error:" + str + " --> " + s);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}