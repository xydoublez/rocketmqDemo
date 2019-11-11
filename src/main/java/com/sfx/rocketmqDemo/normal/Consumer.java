package com.sfx.rocketmqDemo.normal;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    static final String NAMESRV_ADDR="10.68.5.195:9876";
    public static void main(String[] args) throws MQClientException {
        //1.创建消费者对象并指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.设置NameSrv地址
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        //3.订阅消息并绑定监听处理消息
        consumer.subscribe("topic1","tag1");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg :msgs) {

                    System.out.print(new String(msg.getBody())+",");
                    System.out.println(msg);


                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //4.启动
        consumer.start();
        System.out.println("消费者启动成功");

   }


}
