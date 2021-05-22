package com.ken.listeners.fanoutExchange;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 消费者消息队列监听器
 **/
@Component
@RabbitListener(queues = "fanoutQueue2")
public class FanoutListener02 {
    //处理消息方法
    @RabbitHandler
    public void simpleHandler(String msg){
        System.out.println("fanout_queue02 接收消息 == " + msg);
    }
}
