package com.spring.event;

import com.spring.event.OrderEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 有序监听
 */
@Component
public class AutoListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        System.out.println("类型对比"+aClass.toString());
        return aClass == OrderEvent.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("执行事件逻辑");
    }

    @Override
    public boolean supportsSourceType(@Nullable Class<?> sourceType) {
        System.out.println("sourceType对比"+sourceType.toString());
        return sourceType == String.class;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
