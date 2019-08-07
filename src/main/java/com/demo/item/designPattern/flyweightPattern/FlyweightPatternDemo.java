package com.demo.item.designPattern.flyweightPattern;

public class FlyweightPatternDemo {

    private static final String colors[] =
            { "Red", "Green", "Blue", "White", "Black" };
    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
            Cat cat = (Cat) AnimalFactory.getAnimal(getRandomColor());
            cat.setFood("鱼摆摆");
            cat.eat();
        }
    }
    private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
    }
}
