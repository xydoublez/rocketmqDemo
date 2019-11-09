package com.sfx.rocketmqDemo.normal;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 同步生产者，生产信息
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象并制定生产者组名
        DefaultMQProducer producer =  new DefaultMQProducer("group1");
        //2.设置namesrv的地址
        producer.setNamesrvAddr("192.168.253.152:9876");
        //3.启动producer
        producer.start();
        //4.创建消息内容并发送消息
        for (int i = 0; i < 3; i++) {
            String msgStr = "hello_world" +i;
            Message msg = new Message("topic1","tag1",msgStr.getBytes());
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
        //5.关闭producer
        producer.shutdown();


    }
}
