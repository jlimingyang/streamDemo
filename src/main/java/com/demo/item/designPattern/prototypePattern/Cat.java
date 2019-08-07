package com.demo.item.designPattern.prototypePattern;

public class Cat extends Animal {
    @Override
    void eat() {
        System.out.println(name+"在吃东西...");
    }

    public  Cat(){
        name="小猫";
    }
}
