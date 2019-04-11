package com.item.designPattern.facadePattern;

public class AnimalMaker {

    private Animal cat;

    private Animal dog;

    public AnimalMaker() {
        cat = new Cat();
        dog = new Dog();
    }

    public void dog(){
        dog.eat();
    }

    public void cat(){
        cat.eat();
    }
}
