package org.nlb.RabbitMQ.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    private static final String queueName = "queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);
        String msg = "hello RabbitMQ";
        channel.basicPublish("",queueName,null,msg.getBytes());
        channel.close();
        connection.close();
        System.out.println("连接完毕");
    }
}
