package com.item.designPattern.bridgePattern;

public abstract class AnimalAbs {

    protected EatApi eatApi;

    protected AnimalAbs(EatApi eatApi) {
        this.eatApi = eatApi;
    }

    public abstract void eat();
}
