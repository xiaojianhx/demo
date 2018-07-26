//package com.xiaojianhx.demo.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.Consumer;
//import com.rabbitmq.client.QueueingConsumer;
//import com.xiaojianhx.demo.rabbitmq.point.QueueConsumer;
//
//public class Worker implements Runnable {
//
//    private static final String ORDER_QUEUE = "order";
//
//    private Channel channel;
//    private Consumer consumer;
//
//    public Worker() {
//
//        try {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost("localhost");
//            Connection connection = factory.newConnection();
//            channel = connection.createChannel();
//            channel.queueDeclare(ORDER_QUEUE, true, false, false, null);
//            channel.basicQos(1);
//            consumer = new QueueConsumer(channel);
//            channel.basicConsume(ORDER_QUEUE, false, consumer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run() {
//
//        int count = 0;
//
//        while (true) {
//
//            try {
//                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                String message = new String(delivery.getBody());
//                System.out.println(count++ + "次," + message + ",");
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
