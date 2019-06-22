package org.nlb.RabbitMQ.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    private static final String queueName = "work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName,true,false,false,null);
        channel.basicQos(1);//消费者处理完再发送
        for (int i=1;i<=20;i++) {
            String msg = "work "+i;
            Thread.sleep(20);
            channel.basicPublish("",queueName,null,msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
