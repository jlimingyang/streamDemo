package com.demo.item.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.demo.item.rabbitmq.MQConstant.*;

@Component
public class Consum {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = POSTER_QUEUENAME, durable = "true"),
            exchange = @Exchange(value = POSTER_EXCHANGE, durable = "true"),
            key = "DELIVERY-TRANCATE-OPERATION-ROUTING.*"))
    public void customer(String message) {
        System.out.println("exchangeName:" + POSTER_EXCHANGE + " queueName:" + POSTER_QUEUENAME);
        System.out.println(message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = BOSS_QUEUENAME, durable = "true"),
            exchange = @Exchange(value = BOSS_EXCHANGE, durable = "true"),
            key = "BOSS-TRADERECORD-ROUTING.*"))
    public void bossCustomer(String message) {
        System.out.println("exchangeName:" + BOSS_EXCHANGE + " queueName:" + BOSS_QUEUENAME);
        System.out.println(message);
    }
}
