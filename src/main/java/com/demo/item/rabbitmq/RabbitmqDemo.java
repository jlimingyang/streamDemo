package com.demo.item.rabbitmq;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.Application;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

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

    public final String msg0 = "{\"createAccount\":\"5a232d65d599437e28034eba\",\"depotCode\":\"SC00281000052\",\"id\":1,\"orderType\":-1,\"sourceNo\":\"03156497102757511131855\",\"tradeAmount\":0,\"tradeNo\":\"654446471109742592\",\"tradeTime\":1564971028000,\"tradeType\":1}";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        final EmployeeBean employeeBean = JSON.parseObject(testData, EmployeeBean.class);
        ArrayList<EmployeeBean> list = Lists.newArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(employeeBean);
        }
        rabbitTemplate.convertAndSend(POSTER_EXCHANGE,POSTER_ROUTINGKEY,JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect), msg->{
            msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            msg.getMessageProperties().setConsumerQueue(POSTER_QUEUENAME);
            return msg;
        });
    }

    @Test
    public void testSend1(){
        final EmployeeBean employeeBean = JSON.parseObject(testData, EmployeeBean.class);
        ArrayList<EmployeeBean> list = Lists.newArrayList();
        for (int i = 0; i < 30; i++) {
            list.add(employeeBean);
        }
        rabbitTemplate.convertAndSend(BOSS_EXCHANGE,BOSS_ROUTINGKEY,JSON.toJSONString(JSON.parseObject(msg0)), msg->{
            msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            msg.getMessageProperties().setConsumerQueue(BOSS_QUEUENAME);
            return msg;
        });
    }
    @Autowired String redisHost;
    @Test
    public void setTestData001(){
        System.out.println(redisHost);
    }
}
