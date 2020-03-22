package com.demo.item.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TestService {

    public String testA0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testA0";
    }

    public String testB0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testB0";
    }

    public String testC0(ReqVo reqVo) {
        System.out.println("a:" + reqVo.getA() + "b:" + reqVo.getB());
        return "testC0";
    }

    private String testD0(String a, Integer b) {
        System.out.println("a:" + a + "b:" + b);
        return "testD0";
    }

    @Data
    public static class ReqVo {
        String a;
        Integer b;
    }

    @Test
    public void testE0() {
        List<Employee> es = Lists.newArrayList();
        Employee e1 = new Employee();
        e1.setAge(11);
        e1.setName("lisi");
        e1.setGender("nan");
        Employee e2 = new Employee();
        e1.setAge(12);
        e1.setName("lisi");
        e1.setGender("nan");
        Employee e3 = new Employee();
        e1.setAge(13);
        e1.setName("lisi");
        e1.setGender("nan");
        es.add(e1);
        es.add(e2);
        es.add(e3);
        List<Employee> collect = es.stream().peek(s -> s.setAge(100)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));

    }


    public static void main(String[] args) {
        String a = "2019-11-04T16:00:00";
        LocalDateTime l = LocalDateTime.parse(a, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println(l.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
