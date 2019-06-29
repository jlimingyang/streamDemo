package com.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 注解实现方式
 */
@Component
public class AnonaListener {

    @EventListener
    public void test01(OrderEvent orderEvent){
        System.out.println("注解方式发送短信"+orderEvent.getSource());
    }

    @EventListener
    public void test02(OrderEvent orderEvent){
        System.out.println("注解方式发送微信"+orderEvent.getSource());
    }
}
