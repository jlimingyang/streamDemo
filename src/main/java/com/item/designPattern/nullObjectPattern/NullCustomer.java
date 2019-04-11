package com.item.designPattern.nullObjectPattern;

public class NullCustomer extends AbstractCustomer{
    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public String getName() {
        return "null";
    }
}
