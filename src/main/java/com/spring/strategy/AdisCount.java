package com.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class AdisCount implements DiscountStategy {
    @Override
    public String type() {
        return "a";
    }

    @Override
    public double discount(double fee) {
        return fee;
    }
}
