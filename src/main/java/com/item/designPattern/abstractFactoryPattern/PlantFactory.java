package com.item.designPattern.abstractFactoryPattern;

public class PlantFactory extends AbstractFactory {


    @Override
    public Animal getAnimal(String animal) {
        return null;
    }

    @Override
    public Plant getPlant(String plant) {
        if (plant == null) {
            return null;
        }
        if (plant.equalsIgnoreCase("tree")) {
            return new Tree();
        } else if (plant.equalsIgnoreCase("flower")) {
            return new Flower();
        }
        return null;
    }
}
