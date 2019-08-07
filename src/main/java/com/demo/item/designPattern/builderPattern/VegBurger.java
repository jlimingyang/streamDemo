package com.demo.item.designPattern.builderPattern;

public class VegBurger extends Burger{
    @Override
    public String name() {
        return "素汉堡";
    }

    @Override
    public float price() {
        return 33.3f;
    }
}
