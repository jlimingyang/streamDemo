package com.demo.item.ano;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDemo {

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 1, 48, 49, 50);
        takeUser(useCases,User.class);
    }

    public static void takeUser(List<Integer> userCases,Class<?> clazz){
        for (Method m : clazz.getDeclaredMethods()){
            UserCase userCase = m.getAnnotation(UserCase.class);
            System.out.println(userCase.id());
            System.out.println(userCase.desc());
        }
    }
}
