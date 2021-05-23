package com.ken.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 目标：搭建RabbitMQ高级特性演示环境
 * 1.搭建消费者工程【复用之前工程】
 * 2.搭建提供者过程【复用之前工程】
 * 3.编写MessageController：用来发送消息：
 *      交换机
 *      路由键
 *      消息内容
 * 4.RabbitMQ配置交换机和队列，及路由键
 * 5.编写消费者监听
 *
 * 思考问题：生产者能百分之百将消息发送给消息队列吗？
 *
 *  不确定的：
 *  1.消费者如果发消息给MQ，消息在传输的过程中可能丢失。找不到交换机
 *  2.交换机路由到队列，也存在丢失消息的可能性！
 *
 * 问题解决方案：
 * 1.生产者确认模式【解决】
 * 2.生产者回退模式【解决】
 *
 *
 * 目标：演示生产者确认的效果，消息百分百进入交换机
 * 实现步骤：
 *   1.配置开启生产者确认模式
 *   2.编写生产者确认回调方法：处理业务逻辑
 *   3.在RabbitMQ模板对象中，设置回调逻辑
 *   4.测试请求一下
 *
 * 目标2：消息能够从交换机百分百进入到队列
 *  实现步骤：
 *   1.配置开启生产者回退模式
 *   2.编写生产者回退的回调方法
 *   3.设置回退回调方法
 *   4.测试
 *
 *
 **/
@RestController
public class MessageController {
    //发送消息接口
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //定义发送消息的接口
    @RequestMapping("/direct/sendMsg")
    public String sendMsgtoMQ(String exchange,String routingKey,String msg){
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        return "已投递~~~";
    }
}
