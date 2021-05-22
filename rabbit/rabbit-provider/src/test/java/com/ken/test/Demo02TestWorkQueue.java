package com.ken.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo02TestWorkQueue {
    //目标：编写生产者发送消息到消息队列
    //实现过程：导入Rabbit模板对象，调用api接口
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg(){
        for (int i = 0; i < 10000; i++) {
            rabbitTemplate.convertAndSend("workQueue", "hello 小兔子~");
        }
    }
}
