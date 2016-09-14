package com.xiaojianhx.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        int taskSize = 50;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        List<Future<String>> data = new ArrayList<Future<String>>();

        for (int i = 0; i < taskSize; i++) {

            Future<String> f = pool.submit(() -> {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
                return Thread.currentThread().getName() + "," + new Random().nextInt(100);
            });

            data.add(f);
        }

        pool.shutdown();

        data.forEach(item -> {
            try {
                System.out.println(item.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println(Thread.currentThread().getName());
    }
}