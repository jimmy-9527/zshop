package com.ken.listeners;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * 消费者消息队列监听器
 * 问题1：消费者能不能百分百接收到请求，而且业务逻辑处理出现异常，消息还能不能算接收到呢！？
 *
 * 目标：演示消费者手动确认的过程
 * 实现步骤：
 *  1.编写监听器类：和对于监听的方法，编写手动签收的业务逻辑
 *  2.配置开启手动签收
 *  3.测试
 *
 * 问题2：消息在队列中，如果没有被消费者消费？
 *    TTL--> Time to Live  (存活时间/有效期)
 *  目标：演示消息队列中消息失效超时过程
 *  步骤：
 *   1.配置新的队列order.B,设置队列内消息的超时时间5s
 *   2.将队列绑定order_exchange交换机上
 *   3.发送消息，测试
 *
 * 问题3：消息发送失败了，消息丢失了？消息有效期到了！
 *
 *     死信队列：当消息失效了，统一进入的一个队列，这个队列称之为死信队列！
 *     主要有三种情况：
 *       1.到达了消息队列容量上限！
 *       2.消费者如果拒绝签收，不重回队列！
 *       3.消息超时了
 *
 *     目标：演示成为死信的过程
 *     步骤：
 *      1.建立死信队列deadQueue
 *      2.建立死信交换机deadExchange
 *      3.死信队列绑定死信交换机：order.dead
 *      4.队列order.B绑定死信交换机
 *      5.队列发送消息，测试死信交换机
 *   需求：
 *     1.新用户注册成功7天后，发送消息问候？
 *     2.下单后，30分钟未支付，取消订单，回滚票？
 *
 *   延迟队列：消息进入队列后不会被消费，只有到达指定的时间后才会被消费！
 *
 **/
@Component
@RabbitListener(queues = "order.A")
public class ConsumerAckQueueListener {
    //处理消息方法
    @RabbitHandler

    public void simpleHandler(String msg, Message message, Channel channel) throws IOException {

        System.out.println("下单消息{},内容为：" + msg);
        //获取消息的投递标签
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
//            if (msg.contains("苹果")) {
//                throw new RuntimeException(" 不允许售卖苹果手机！");
//            }

            //签收消息：
            /**
             * 参数1：投递标签
             * 参数2：是否是批量签收，true一次性签收所有消息，如果false则只签收当前消息
             */
            channel.basicAck(deliveryTag, false);
            System.out.println("签收成功{}");
            //拒绝签收消息：出现异常了，拒绝签收
        } catch (IOException e) {
            //e.printStackTrace();
            //参数1：投递标签
            //参数2：是否批量
            //参数3：是否重回队列
            channel.basicNack(deliveryTag, false, true);
            System.out.println("签收失败{}");
        }
    }
}
