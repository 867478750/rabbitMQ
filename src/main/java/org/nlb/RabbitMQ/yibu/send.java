package org.nlb.RabbitMQ.yibu;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("tx",false,false,false,null);
            channel.confirmSelect();

        for (int i=1;i<10;i++) {
            String msg = "tx";
            channel.basicPublish("","tx",null,msg.getBytes());
        }
        if(!channel.waitForConfirms()){
                System.out.println("失败");
            }else {
                System.out.println("成功");
            }

        channel.close();
        connection.close();

    }
}
