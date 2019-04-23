package com.item.designPattern.proxyPattern.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {

    private Object readObj;

    public DynamicProxyHandler(Object readObj){
        this.readObj = readObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我在执行代理的东西");
        return method.invoke(readObj,args);
    }
}
