package com.spring.strategy;

public interface DiscountStategy {
    public String type();

    public double discount(double fee);
}
