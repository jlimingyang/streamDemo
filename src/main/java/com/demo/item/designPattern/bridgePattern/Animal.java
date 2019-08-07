package com.demo.item.designPattern.bridgePattern;

public class Animal extends AnimalAbs{
    private String food;

    public Animal(EatApi eatApi,String food) {
        super(eatApi);
        this.food = food;
    }

    @Override
    public void eat() {
        eatApi.eat(food);
    }
}
