package com.demo.item.designPattern.flyweightPattern;

public class Cat implements Animal {

    private String food;

    private String name;


    public void setFood(String food) {
        this.food = food;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println(name+"正在吃"+food);
    }
}
