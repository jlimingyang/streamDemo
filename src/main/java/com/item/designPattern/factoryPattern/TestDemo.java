package com.item.designPattern.factoryPattern;

public class TestDemo {

    public static void main(String[] args) {
        TestFactory t = new TestFactory();
        Test t0 = t.getTestFactory("Test00");
        Test t1 = t.getTestFactory("Test01");
        Test t2 = t.getTestFactory("Test02");
        t0.wdful();
        t1.wdful();
        t2.wdful();
    }
}
