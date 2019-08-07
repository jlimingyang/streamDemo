package com.demo.item.designPattern.facadePattern;

public class FacadePatternDemo {

    public static void main(String[] args) {
        AnimalMaker a= new AnimalMaker();
        a.cat();
        a.dog();
    }
}
