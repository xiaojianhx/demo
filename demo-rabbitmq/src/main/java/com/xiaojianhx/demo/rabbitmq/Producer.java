package com.xiaojianhx.demo.rabbitmq;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Producer {

    private static final String TASK_QUEUE_NAME = "order_queue";

    public static void main(String[] args) throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 指定队列持久化
        DeclareOk ok = channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        // 指定消息持久化
        channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, getMessage(args).getBytes());

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings) {

        if (strings.length < 1) {
            return "Hello World!";
        }

        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {

        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
