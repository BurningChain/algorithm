package com.miracle.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumer {


    public static void main(String[] args) {

        Channel channel = null;
        try {
            channel = MqUtils.getChannel();
            channel.queueDeclare("test", true, false, false, null);
            channel.queueBind("test", "testExchanger", "tt");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Customer Received '" + message + "'");
                }
            };
            //自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume("test", true, consumer);

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }


        while (true) {



        }

    }


}
