package com.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * 实现事件方式的实现无序监听
 */
@Component
public class WxListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent orderEvent) {
        System.out.println(orderEvent.getSource());
        System.out.println("发送微信");
    }
}
