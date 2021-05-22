package com.ken.listeners.routingQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "routingQueue2")
public class RoutingListener2 {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg){
        System.out.println("routing_queue2 接收消息 == " + msg);
    }
}
