package com.miracle.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqUtils {



    public static Channel getChannel() throws IOException, TimeoutException {

        Connection connection = getConnectionFactory().newConnection();
        return connection.createChannel();
    }





    public static ConnectionFactory getConnectionFactory() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.219.222");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setConnectionTimeout(5000);
        return connectionFactory;
    }
}
