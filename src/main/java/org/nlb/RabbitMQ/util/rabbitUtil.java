package org.nlb.RabbitMQ.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class rabbitUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);
        factory.setUsername("nlb");
        factory.setHost("localhost");
        factory.setPassword("sunshine");
        factory.setVirtualHost("nlb");
        return factory.newConnection();
    }

}
