package com.xiaojianhx.demo.designpattern.singleton;

import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

    @Test
    public void test1() {

        var size = 10;

        var hashcodeSet = new TreeSet<Integer>();

        var cdl = new CountDownLatch(10);
        for (var i = 0; i < size; i++) {

            var t = new Thread(() -> {
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

        var size = 10;

        var hashcodeSet = new TreeSet<Integer>();

        var cdl = new CountDownLatch(10);
        for (var i = 0; i < size; i++) {

            var t = new Thread(() -> {
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

        var size = 10;

        var hashcodeSet = new TreeSet<Integer>();

        var cdl = new CountDownLatch(10);
        for (var i = 0; i < size; i++) {

            var t = new Thread(() -> {
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

        var size = 10;

        var hashcodeSet = new TreeSet<Integer>();

        var cdl = new CountDownLatch(10);
        for (var i = 0; i < size; i++) {

            var t = new Thread(() -> {
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