package com.demo.item.designPattern.mediatorPattern;

public class MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("张三");
        User john = new User("李四");

        robert.sendMessage("Hi! 张三!");
        john.sendMessage("Hello! 李四!");
    }
}
