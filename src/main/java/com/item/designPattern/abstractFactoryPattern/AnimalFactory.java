package com.item.designPattern.abstractFactoryPattern;

public class AnimalFactory extends AbstractFactory {
    @Override
    public Animal getAnimal(String animal) {
        if(animal == null){
            return null;
        }
        if(animal.equalsIgnoreCase("Cat")){
            return new Cat();
        } else if(animal.equalsIgnoreCase("Dog")){
            return new Dog();
        }
        return null;
    }

    @Override
    public Plant getPlant(String plant) {
        return null;
    }
}
