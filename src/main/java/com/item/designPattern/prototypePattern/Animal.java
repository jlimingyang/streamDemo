package com.item.designPattern.prototypePattern;

public abstract class Animal implements Cloneable {

    private String id;
    protected String name;

    abstract void eat();

    public String getId() {
        return id;
    }

    public Animal setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
