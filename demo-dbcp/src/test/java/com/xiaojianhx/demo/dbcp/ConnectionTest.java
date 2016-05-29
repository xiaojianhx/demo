package com.xiaojianhx.demo.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionTest {

    @Test
    public void testConnection1() {

        int size = 10000;
        Set<Integer> data = new TreeSet<>();
        for (int i = 0; i < size; i++) {

            Connection conn = ConnectionPoolFactory.getInstance().getConnection();
            data.add(conn.hashCode());
            Assert.assertNotNull(conn);

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(1, data.size());
    }

    @Test
    public void testConnection2() {

        int size = 10000;

        Set<Integer> data = new TreeSet<>();
        CountDownLatch latch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            new Thread(new ConnectionThread(latch, data)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data.size());
        Assert.assertEquals(size, data.size());
    }

    class ConnectionThread implements Runnable {

        private CountDownLatch latch;
        private Set<Integer> data;

        public ConnectionThread(CountDownLatch latch, Set<Integer> data) {
            this.latch = latch;
            this.data = data;
        }

        public void run() {
            Connection conn = ConnectionPoolFactory.getInstance().getConnection();
            data.add(conn.hashCode());
            System.out.println(Thread.currentThread().getName() + "\t" + conn.hashCode());
            latch.countDown();

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
