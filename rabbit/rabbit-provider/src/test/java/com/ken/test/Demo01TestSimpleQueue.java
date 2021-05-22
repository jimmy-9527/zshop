package com.ken.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo01TestSimpleQueue {
    //目标：编写生产者发送消息到消息队列
    //实现过程：导入Rabbit模板对象，调用api接口
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg(){
        /**
         * 参数1：队列名称
         * 参数2：消息内容
         */
        rabbitTemplate.convertAndSend("myQueue", "hello 小兔子~");
    }
}
