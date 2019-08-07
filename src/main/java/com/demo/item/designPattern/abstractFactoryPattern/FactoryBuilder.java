package com.demo.item.designPattern.abstractFactoryPattern;

public class FactoryBuilder {

    public static AbstractFactory getFactory(String factory){
        if(factory.equalsIgnoreCase("animal")){
            return new AnimalFactory();
        }else if(factory.equalsIgnoreCase("plant")){
            return new PlantFactory();
        }
        return null;
    }
}
