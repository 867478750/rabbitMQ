package org.nlb.RabbitMQ.work;

import com.rabbitmq.client.*;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class resv1 {
    public static final String queueName = "work";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = rabbitUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(queueName,true,false,false,null);
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("[1]"+msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        boolean auto = false ;
        channel.basicConsume("work",auto,defaultConsumer);

    }
}
