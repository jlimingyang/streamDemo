package com.demo.item.designPattern.bridgePattern;

public class AnimalDemo {

    public static void main(String[] args) {
        Animal a = new Animal(new Dog(), "屎");
        Animal b = new Animal(new Cat(), "鱼");
        a.eat();
        b.eat();
    }
}
