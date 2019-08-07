package com.demo.item.designPattern.bridgePattern;

public class Dog implements EatApi {
    @Override
    public void eat(String food) {
        System.out.println("狗要吃"+food);
    }
}
