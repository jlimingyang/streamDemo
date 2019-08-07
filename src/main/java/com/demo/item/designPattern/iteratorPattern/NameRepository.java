package com.demo.item.designPattern.iteratorPattern;

public class NameRepository implements Container {

    public String names[] = {"a1" , "b1" ,"c1" , "d1"};
    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    public class NameIterator implements Iterator{

        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
