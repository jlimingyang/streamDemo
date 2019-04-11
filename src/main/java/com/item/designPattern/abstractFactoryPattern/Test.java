package com.item.designPattern.abstractFactoryPattern;

public class Test {

    public static void main(String[] args) {
        AbstractFactory f = FactoryBuilder.getFactory("animal");
        Animal d = f.getAnimal("dog");
        d.eat();
        Animal c = f.getAnimal("cat");
        c.eat();
        f = FactoryBuilder.getFactory("plant");
        Plant flower = f.getPlant("flower");
        Plant tree = f.getPlant("tree");
        flower.gemmation();
        tree.gemmation();
    }
}
