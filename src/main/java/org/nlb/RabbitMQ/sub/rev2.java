package org.nlb.RabbitMQ.sub;

import com.rabbitmq.client.*;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class rev2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("sub",false,false,false,null);
        channel.queueBind("sub","subExchange","");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("[2]"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("sub",false,consumer);

    }
}
