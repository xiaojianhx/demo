package com.xiaojianhx.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Producer {

    private static final String ORDER_QUEUE = "order";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(ORDER_QUEUE, true, false, false, null);

        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", ORDER_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, ("测试" + i).getBytes());
        }

        channel.close();
        connection.close();
    }
}