package com.demo.item.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.demo.item.rabbitmq.MQConstant.*;

@Configuration
public class MQConfig {

    @Bean
    public DirectExchange DExchange() {
        return new DirectExchange(POSTER_EXCHANGE, true, false);
    }

    @Bean
    public Queue queue1() {
        return new Queue(POSTER_QUEUENAME);
    }

    @Bean
    public DirectExchange BExchange() {
        return new DirectExchange(BOSS_EXCHANGE, true, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(BOSS_QUEUENAME);
    }

    @Bean
    public Binding binding1(Queue queue1, DirectExchange DExchange) {
        return BindingBuilder.bind(queue1).to(DExchange).with(POSTER_ROUTINGKEY);
    }

    @Bean
    public Binding binding2(Queue queue2, DirectExchange BExchange) {
        return BindingBuilder.bind(queue2).to(BExchange).with(BOSS_ROUTINGKEY);
    }

//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    @Bean
//    public RabbitTemplate registRabbit(){
//        return new RabbitTemplate(connectionFactory());
//    }

}
