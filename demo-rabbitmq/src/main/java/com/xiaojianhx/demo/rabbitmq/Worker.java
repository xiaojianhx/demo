package com.xiaojianhx.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {

    private static final String ORDER_QUEUE = "order";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(ORDER_QUEUE, true, false, false, null);

        // 指定该消费者同时只接收一条消息
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(ORDER_QUEUE, false, consumer);

        int count = 0;

        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(count++ + "次," + message + ",");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
        }
    }
}
