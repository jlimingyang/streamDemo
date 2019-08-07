package com.demo.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class Cdiscount implements DiscountStategy {
    @Override
    public String type() {
        return "c";
    }

    @Override
    public double discount(double fee) {
        return fee*0.8;
    }
}
