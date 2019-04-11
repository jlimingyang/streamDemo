package com.item.designPattern.prototypePattern;

public class PrototypePatternDemo {

    public static void main(String[] args) {
        AnimalCache.loadCache();
        Dog dog = (Dog)AnimalCache.getAnimal("1");
        dog.eat();
        Cat cat = (Cat)AnimalCache.getAnimal("2");
        cat.eat();

    }
}
