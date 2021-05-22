package com.ken.listeners.simpleQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "myQueue")
public class SimpleListener {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg) {
        System.out.println("接收消息 == " + msg);
    }
}
