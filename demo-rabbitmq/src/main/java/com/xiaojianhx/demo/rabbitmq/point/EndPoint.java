package com.xiaojianhx.demo.rabbitmq.point;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endpointName) throws Exception {

        this.endPointName = endpointName;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(endpointName, false, false, false, null);
    }

    public void close() throws Exception {
        channel.close();
        connection.close();
    }
}