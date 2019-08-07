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
            value =@Queue(value = QUEUENAME, durable = "true"),
            exchange = @Exchange(value = EXCHANGE, durable = "true"),
            key = ROUTINGKEY))
    public void customer(String message) {
        System.out.println(message);
    }
}
