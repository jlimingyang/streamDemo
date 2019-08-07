package com.demo.item.designPattern.builderPattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("素的");
        vegMeal.showItems();
        System.out.println("总价: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("荤的");
        nonVegMeal.showItems();
        System.out.println("总价: " + nonVegMeal.getCost());
    }
}
