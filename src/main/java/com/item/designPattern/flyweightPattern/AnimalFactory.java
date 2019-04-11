package com.item.designPattern.flyweightPattern;

import java.util.HashMap;

public class AnimalFactory {

    private static final HashMap<String, Animal> animalMap = new HashMap<>();


    public static Animal getAnimal(String name) {
        Cat cat = (Cat) animalMap.get(name);

        if (cat == null) {
            cat = new Cat(name);
            animalMap.put(name, cat);
            System.out.println("Creating Animal: " + name);
        }
        return cat;
    }

}
