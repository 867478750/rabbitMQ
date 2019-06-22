package org.nlb.RabbitMQ.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    private static final String exchangeName = "direct";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName,"direct");
        channel.basicQos(1);
        String msg = "direct";
        String bind = "error2";
        channel.basicPublish(exchangeName,bind,null,msg.getBytes());
        channel.close();
        connection.close();
    }

}
