package com.ken.listeners.topicQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "topicQueue1")
public class TopicListener1 {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg){
        System.out.println(" topicQueue1 接收消息 == " + msg);
    }
}
