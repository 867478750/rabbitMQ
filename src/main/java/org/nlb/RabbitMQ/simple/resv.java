package org.nlb.RabbitMQ.simple;

import com.rabbitmq.client.*;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class resv {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue",false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("接收到的消息"+msg);
            }
        };
        channel.basicConsume("queue",true,consumer);

    }
}
