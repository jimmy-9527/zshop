package com.ken.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo05TestTopicExchange {
    //目标：通过发布订阅模式，发送消息到交换机
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg(){
        rabbitTemplate.convertAndSend("topicExchange","item.insert", "hello 小兔子~【item.insert】");
        rabbitTemplate.convertAndSend("topicExchange","item.insert.abc", "hello 小兔子~ 【item.insert.abc】");
    }
}
