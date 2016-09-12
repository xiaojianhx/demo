package com.xiaojianhx.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) {

        int taskSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        List<Future> data = new ArrayList<Future>();

        for (int i = 0; i < taskSize; i++) {

            Future f = pool.submit(() -> {
                return new Random().nextInt(100);
            });

            data.add(f);
        }

        pool.shutdown();

        data.forEach(System.out::println);
        System.out.println("thread-main");
    }
}