package com.demo.item.designPattern.builderPattern;

public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "可乐";
    }

    @Override
    public float price() {
        return 55.5f;
    }
}
