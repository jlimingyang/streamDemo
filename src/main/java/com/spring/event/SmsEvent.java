package com.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class SmsEvent extends ApplicationEvent {
    public SmsEvent(Object source,String msg) {
        super(source);
        log.debug("发送消息");
    }
}
