package com.demo.item.rabbitmq;

public class MQConstant {


    /**
     * 推送到Poster端专用
     **/
    public final static String EXCHANGE = "DELIVERY-EXCHANGE-TEST";
    public final static String QUEUENAME = "DELIVERY-TRANCATE-OPERATION-QUEUE-TEST";
    public final static String ROUTINGKEY = "DELIVERY-TRANCATE-OPERATION-ROUTING-TEST";

    /**
     * 推送到BOSS端专用
     **/
    public final static String BOSS_EXCHANGE = "BOSS-EXCHANGE-TEST";
    public final static String BOSS_QUEUENAME = "BOSS-TRADERECORD-QUEUE-TEST";
    public final static String BOSS_ROUTINGKEY = "BOSS-TRADERECORD-ROUTING-TEST";
}
