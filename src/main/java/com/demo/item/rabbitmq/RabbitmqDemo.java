package com.demo.item.rabbitmq;

import com.demo.Application;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.demo.item.rabbitmq.MQConstant.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class RabbitmqDemo {



    private final static String testData = "{\n" +
            "\"employees\": [\n" +
            "{ \"firstName\":\"John\" , \"lastName\":\"Doe\" },\n" +
            "{ \"firstName\":\"Anna\" , \"lastName\":\"Smith\" },\n" +
            "{ \"firstName\":\"Peter\" , \"lastName\":\"Jones\" }\n" +
            "]\n" +
            "}";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        final EmployeeBean employeeBean = JSON.parseObject(testData, EmployeeBean.class);
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTINGKEY,JSON.toJSONString(employeeBean),msg->{
            msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            msg.getMessageProperties().setConsumerQueue(QUEUENAME);
            return msg;
        });
    }
}
