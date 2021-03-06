package com.xiaojianhx.demo.rabbitmq.point;

import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws Exception {
        super(endPointName);
    }

    public void run() {
        try {
            System.out.println("endPointName:" + endPointName);
            channel.basicConsume(endPointName, true, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) {
        Map map = (Map) SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("message number") + " received.");
    }

    public void handleCancel(String consumerTag) {
    }

    public void handleCancelOk(String consumerTag) {
    }

    public void handleRecoverOk(String consumerTag) {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }
}