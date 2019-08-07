package com.demo.item.ano;

public class User {

    @UserCase(id="1",desc = "test01")
    public void test01(String age){
        System.out.println("年龄:"+age);
    }

    @UserCase(id="2")
    public void test02(String age){
        System.out.println("年龄:"+age);
    }
}
