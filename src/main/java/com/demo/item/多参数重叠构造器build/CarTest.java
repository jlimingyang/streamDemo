package com.demo.item.多参数重叠构造器build;

import org.junit.Test;

public class CarTest {
    @Test
    public void test01(){
        Car car = new Car.CarBuilder().age(1).day("20").des("12312sddasd").build();
        System.out.println(car);
    }
}
