package com.xiaojianhx.demo.rabbitmq;

import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class Producer implements Runnable {

    private static final String ORDER_QUEUE = "order";

    private Channel channel;
    private String message;

    private CountDownLatch latch;

    public Producer(CountDownLatch latch, Channel channel, String message) {
        this.latch = latch;
        this.channel = channel;
        this.message = message;
    }

    public void run() {
        try {
            channel.basicPublish("", ORDER_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        latch.countDown();
    }
}