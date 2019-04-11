package com.item.designPattern.bridgePattern;

public class Cat implements EatApi {
    @Override
    public void eat(String food) {
        System.out.println("猫要吃"+food);
    }
}
