package org.nlb.RabbitMQ.direct;

import com.rabbitmq.client.*;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class resv1 {
    private static final String exchangeName = "direct" ;
    private static final String queueName = "direct_1" ;

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        String bind_1 = "error";
        String bing_2 = "error1";
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,exchangeName,bind_1);
        channel.queueBind(queueName,exchangeName,bing_2);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("[1]"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(queueName,false,consumer);
    }
}
