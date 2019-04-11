package com.item.designPattern.factoryPattern;

import java.util.Objects;

public class TestFactory {

    public Test getTestFactory(String test) {
        if (Objects.isNull(test)) {
            return null;
        }
        if (test.equalsIgnoreCase("Test00")) {
            return new Test00();
        } else if (test.equalsIgnoreCase("Test01")) {
            return new Test01();
        } else if (test.equalsIgnoreCase("Test02")) {
            return new Test02();
        }
        return null;
    }
}
