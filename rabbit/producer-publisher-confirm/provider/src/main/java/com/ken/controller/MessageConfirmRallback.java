package com.ken.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
 * f发送消息回调确认类：消息如果没有进入交换机，会回调当前类中的returnedMessage
 **/
@Component
public class MessageConfirmRallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback  {
    //配置回调的方法
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //配置在当前对象注入之后，在设置当前对象到RabbitTemplate对象中
    @PostConstruct//注解作用：在当前对象初始化完毕之后执行的方法
    public void initRabbittemplate(){
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

    /**
     * 不论是否进入交换机，都会回调当前方法
     * @param correlationData 消息投递封装对象
     * @param ack 是否投递成功
     * @param exception 如果错误，错误原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String exception) {
        if (ack) {
            System.out.println("消息进入了交换机成功{}");
        }else {
            System.out.println("消息进入了交换机失败{} 原因：" + exception);
        }
    }

    /**
     * 消息从交换机进入队列失败回调方法：只会在失败的情况下
     * @param message the returned message.
     * @param replyCode the reply code.
     * @param replyText the reply text.
     * @param exchange the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息从交换机进入队列失败：>>>>>>>");
        System.out.println("exchange = " + exchange);
        System.out.println("replyCode = " + replyCode);
        System.out.println("replyText = " + replyText);
        System.out.println("routingKey = " + routingKey);
    }
}
