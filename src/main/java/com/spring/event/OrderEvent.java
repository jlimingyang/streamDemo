package com.spring.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class OrderEvent extends ApplicationEvent {
    public OrderEvent(Object source,String createOrder) {
        super(source);
        log.debug("订单:{}下单",createOrder);
    }
}
