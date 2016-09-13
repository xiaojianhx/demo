package com.xiaojianhx.demo.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {

        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        System.out.println(Thread.currentThread().getName());
        future.complete(100);

        future.thenComposeAsync((num) -> {
            System.out.println(Thread.currentThread().getName());
            CompletableFuture<Integer> tmp = new CompletableFuture<Integer>();
            tmp.complete(num + 1);
            return tmp;
        }).thenComposeAsync((num) -> {
            System.out.println(Thread.currentThread().getName());
            CompletableFuture<Integer> tmp = new CompletableFuture<Integer>();
            tmp.complete(num + 2);
            return tmp;
        }).whenCompleteAsync((num, ex) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(num);
        });
    }
}