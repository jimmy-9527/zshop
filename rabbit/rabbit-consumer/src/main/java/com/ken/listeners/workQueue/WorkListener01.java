package com.ken.listeners.workQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "workQueue")
public class WorkListener01 {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg) {
        System.out.println("workQueue 01 接收消息 == " + msg);
    }
}
