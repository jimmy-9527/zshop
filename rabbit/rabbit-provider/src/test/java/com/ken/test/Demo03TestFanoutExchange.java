package com.ken.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo03TestFanoutExchange {
    //目标：通过发布订阅模式，发送消息到交换机
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg(){
        for (int i = 0; i < 100; i++) {
            /**
             * 参数1：交换机
             * 参数2：路由键
             * 参数3：消息内容
             */
            rabbitTemplate.convertAndSend("fanoutExchange","", "hello 小兔子~");
        }
    }
}
