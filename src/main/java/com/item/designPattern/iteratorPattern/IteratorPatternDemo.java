package com.item.designPattern.iteratorPattern;

public class IteratorPatternDemo {

    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        for (Iterator it = nameRepository.getIterator();it.hasNext();){
            System.out.println(it.next());
        }
    }
}
