package com.xiaojianhx.demo.concurrent;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

    private static Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            final int a = i;
            new Thread(() -> {
                queue.add(a);
                System.out.println(Thread.currentThread().getName() + "-->" + queue);
            }).start();
        }

        queue.clear();
        queue.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(queue);

        queue.size();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("----" + Thread.currentThread().getName() + "-->" + queue.poll() + "-->" + queue);
            }).start();
        }
    }
}