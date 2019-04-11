package com.item.二进制位运算;

import org.junit.Test;
import org.mortbay.util.ajax.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test01 {

    @Test
    public void test01(){
        List<String> list = new ArrayList<String>(){{
            add("1");
        }};
        String a = list.get(0);
        list.remove(a);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }

    @Test
    public void test02(){
        List<BigDecimal> list = new ArrayList<BigDecimal>(){{
            add(new BigDecimal(1));
            add(new BigDecimal(2));
            add(new BigDecimal(3));
        }};
        System.out.println(JSON.toString(list));
      list = list.stream().sorted((x,y)->y.compareTo(x)).collect(Collectors.toList());
        System.out.println(JSON.toString(list));
        Integer a = null;
        Integer b = 111;
        System.out.println(b.equals(a));
        list = null;
        System.out.println(JSON.toString(list));

    }
}
