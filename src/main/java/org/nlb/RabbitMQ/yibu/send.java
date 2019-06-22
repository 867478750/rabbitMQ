package org.nlb.RabbitMQ.yibu;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import org.nlb.RabbitMQ.util.rabbitUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class send {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = rabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("yibu",false,false,false,null);
        channel.confirmSelect();
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        channel.addConfirmListener(new ConfirmListener() {//进行监听
            public void handleAck(long l, boolean b) throws IOException {
                //没有问题的
                if(b){
                    confirmSet.headSet(l+1).clear();
                }else {
                    confirmSet.remove(l);
                }
            }
            public void handleNack(long l, boolean b) throws IOException {
                //有问题的
                if(b){
                    confirmSet.headSet(l+1).clear();
                }else {
                    confirmSet.remove(l);
                }
            }
        });
        String msg = "msg";



        channel.close();
        connection.close();

    }
}
