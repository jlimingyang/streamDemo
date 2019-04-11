package com.item.designPattern.templatePattern;

public class Football extends Game {
    @Override
    void initialize() {
        System.out.println(this.getClass().getName()+"init");
    }

    @Override
    void startPlay() {
        System.out.println(this.getClass().getName()+"playing");
    }

    @Override
    void endPlay() {
        System.out.println(this.getClass().getName()+"game over");
    }
}
