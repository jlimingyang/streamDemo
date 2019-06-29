package com.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class BdisCount implements DiscountStategy {
    @Override
    public String type() {
        return "b";
    }

    @Override
    public double discount(double fee) {
        return fee*0.9;
    }
}
