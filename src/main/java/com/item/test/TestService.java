package com.item.test;

import lombok.Data;

public class TestService {

    public String testA0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testA0";
    }

    public String testB0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testB0";
    }

    public String testC0(ReqVo reqVo ) {
        System.out.println("a:" + reqVo.getA() + "b:" + reqVo.getB());
        return "testC0";
    }

    private String testD0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testD0";
    }

    @Data
    public static class ReqVo{
        String a;
        Integer b;
    }
}
