package org.nlb.RabbitMQ.sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    private static final String exchangeName = "subExchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName,"fanout");

        for (int i=1;i<=10;i++) {
            String msg = "sub"+i;
            channel.basicPublish(exchangeName,"",null,msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
