package com.demo.item.designPattern.prototypePattern;

import java.util.Hashtable;

public class AnimalCache {

    private static Hashtable<String, Animal> animalHashtable = new Hashtable<>();

    public static Animal getAnimal(String animalId) {
        Animal animal = animalHashtable.get(animalId);
        return (Animal) animal.clone();
    }

    public static void loadCache() {
        Dog dog = new Dog();
        dog.setId("1");
        animalHashtable.put(dog.getId(),dog);

        Cat cat = new Cat();
        cat.setId("2");
        animalHashtable.put(cat.getId(),cat);
    }
}
