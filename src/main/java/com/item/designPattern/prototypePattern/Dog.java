package com.item.designPattern.prototypePattern;

public class Dog extends Animal{
    @Override
    void eat() {
        System.out.println(name+"在吃东西....");
    }
    public Dog(){
        name = "小狗";
    }
}
