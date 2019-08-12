package com.demo.item.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConstant {

    @Configuration
    @ConditionalOnProperty("constant.enable")
    public static class Constant {
        @Value("${spring.redis.host}")
        private String redis_host;

        @Bean
        public String redisHost() {
            return redis_host;
        }

    }

    /**
     * 推送到Poster端专用
     **/
    public final static String POSTER_EXCHANGE = "DELIVERY-EXCHANGE";
    public final static String POSTER_QUEUENAME = "DELIVERY-TRANCATE-OPERATION-QUEUE";
    public final static String POSTER_ROUTINGKEY = "DELIVERY-TRANCATE-OPERATION-ROUTING";

    /**
     * 推送到BOSS端专用
     **/
    public final static String BOSS_EXCHANGE = "BOSS-EXCHANGE";
    public final static String BOSS_QUEUENAME = "BOSS-TRADERECORD-QUEUE";
    public final static String BOSS_ROUTINGKEY = "BOSS-TRADERECORD-ROUTING";

}
