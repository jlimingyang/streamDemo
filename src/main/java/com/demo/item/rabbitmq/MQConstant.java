package com.demo.item.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("constant.enable")
@Slf4j
public class MQConstant {

    @Value("${spring.redis.host}")
    public String redisHost;

    /**
     * 推送到Poster端专用
     **/
    @Value("${constant.enable}")
    void intParam(Boolean enable) {
        log.debug("constant enable:{}", enable);
        if (enable) {
            try {
                REDIS_HOST = redisHost;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String REDIS_HOST;
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
