package com.demo.item.rabbitmq;

public class MQConstant {


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
