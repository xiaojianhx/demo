package com.xiaojianhx.demo.rabbitmq;

import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class P {

    private static final String ORDER_QUEUE = "order";

    private static Connection connection;
    private static Channel channel;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int count = 10000000;
        CountDownLatch latch = new CountDownLatch(count);
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(ORDER_QUEUE, true, false, false, null);

            for (int i = 0; i < count; i++) {
                new Thread(new Producer(latch, channel, "测试" + i)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("生产完成,用时:" + (System.currentTimeMillis() - start) + "MS");
    }
}
