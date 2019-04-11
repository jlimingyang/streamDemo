package com.item.designPattern.decoratorPattern;

public abstract class ShapeDecorator implements Shape{
    protected Shape decoratorShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratorShape = decoratedShape;
    }

    @Override
    public void draw(){
        decoratorShape.draw();
    }
}
