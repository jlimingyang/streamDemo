package com.demo.item.designPattern.abstractFactoryPattern;

public abstract class AbstractFactory {

    public abstract Animal getAnimal(String animal);
    public abstract Plant getPlant(String plant);
}
