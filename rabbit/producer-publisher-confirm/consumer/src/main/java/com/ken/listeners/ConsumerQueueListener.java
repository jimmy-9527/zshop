package com.ken.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "order.A")
public class ConsumerQueueListener {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg){
        System.out.println("下单消息{},内容为：" + msg);
    }
}
