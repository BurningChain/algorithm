package com.miracle.mq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.jms.JmsProperties;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import static org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode.PERSISTENT;

public class RabbitMQTest {


    public static void main(String[] args) {

        try {
            Channel channel = new RabbitMQTest().getChannel();
            AMQP.Queue.DeclareOk queue = channel.queueDeclare("test", true, false, false, null);
            channel.exchangeDeclare("testExchanger", BuiltinExchangeType.FANOUT);
            channel.queueBind("test", "testExchanger", "tt");
            channel.basicPublish("testExchanger", "fanout", new AMQP.BasicProperties().builder().deliveryMode(2).build(), "hello world".getBytes());
            channel.confirmSelect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }




    public Channel getChannel() throws IOException, TimeoutException {

        Connection connection = getConnectionFactory().newConnection();
        return connection.createChannel();
    }





    public ConnectionFactory getConnectionFactory() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.93.219.222");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setConnectionTimeout(5000);
        return connectionFactory;
    }

}
