package com.item.designPattern.visitorPattern;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
