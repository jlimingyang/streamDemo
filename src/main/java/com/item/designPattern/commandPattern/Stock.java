package com.item.designPattern.commandPattern;

public class Stock {

    private String name = "张三";
    private int num = 20;

    public void buy(){
        System.out.println(name+"买了"+num+"个鸡蛋");
    }
    public void sell(){
        System.out.println(name+"卖了"+num+"个鸡蛋");
    }
}
