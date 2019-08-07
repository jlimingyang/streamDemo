package com.demo.item.designPattern.builderPattern;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print(",商品: "+item.name());
            System.out.print(",包装方式: "+item.packing().packing());
            System.out.println(",价格: "+item.price());
        }
    }
}
