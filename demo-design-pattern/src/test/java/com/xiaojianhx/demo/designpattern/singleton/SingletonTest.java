package com.xiaojianhx.demo.designpattern.singleton;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

    @Test
    public void test1() {

        int size = 10;

        final Set<Integer> hashcodeSet = new TreeSet<>();

        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < size; i++) {

            Thread t = new Thread(() -> {
                hashcodeSet.add(Singleton1.getInstance().hashCode());
                cdl.countDown();
            });
            t.start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hashcodeSet);
        Assert.assertNotEquals(1, hashcodeSet.size());
    }

    @Test
    public void test2() {

        int size = 10;

        final Set<Integer> hashcodeSet = new TreeSet<>();

        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < size; i++) {

            Thread t = new Thread(() -> {
                hashcodeSet.add(Singleton2.getInstance().hashCode());
                cdl.countDown();
            });
            t.start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hashcodeSet);
        Assert.assertEquals(1, hashcodeSet.size());
    }

    @Test
    public void test3() {

        int size = 10;

        final Set<Integer> hashcodeSet = new TreeSet<>();

        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < size; i++) {

            Thread t = new Thread(() -> {
                hashcodeSet.add(Singleton3.getInstance().hashCode());
                cdl.countDown();
            });
            t.start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hashcodeSet);
        Assert.assertEquals(1, hashcodeSet.size());
    }

    @Test
    public void test4() {

        int size = 10;

        final Set<Integer> hashcodeSet = new TreeSet<>();

        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < size; i++) {

            Thread t = new Thread(() -> {
                hashcodeSet.add(Singleton4.getInstance().hashCode());
                cdl.countDown();
            });
            t.start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, hashcodeSet.size());
    }
}