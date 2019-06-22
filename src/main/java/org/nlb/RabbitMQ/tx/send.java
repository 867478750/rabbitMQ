package org.nlb.RabbitMQ.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("tx",false,false,false,null);
        try {
            channel.txSelect();
            String msg = "tx";
            channel.basicPublish("","tx",null,msg.getBytes());
            channel.txCommit();
        } catch (IOException e) {
            channel.txRollback();
            System.out.println("回滚");
        } finally {
            channel.close();
            connection.close();
        }

    }
}
