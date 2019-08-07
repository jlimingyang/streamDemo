package com.demo.item.designPattern.proxyPattern.dynamicProxy;

import com.demo.item.designPattern.proxyPattern.Image;
import com.demo.item.designPattern.proxyPattern.RealImage;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {
        Image  realImage = (Image)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{Image.class},new DynamicProxyHandler(new RealImage("动态代理")));
        realImage.display();
    }
}
