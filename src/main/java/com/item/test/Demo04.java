package com.item.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Demo04 {
    @Test
    public void testParse(){
        System.out.println(LocalDateTime.of(LocalDate.parse("2018-07-15"), LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8")));
    }

    @Test
    public void testReflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = TestService.class.getDeclaredMethods();
        final Method testC0 = TestService.class.getDeclaredMethod("testC0", TestService.ReqVo.class);
        String name = TestService.class.getName();
        Method[] methods = TestService.class.getMethods();
        Class<?>[] classes = TestService.class.getClasses();
        ClassLoader classLoader = TestService.class.getClassLoader();
        Field[] declaredFields = TestService.class.getDeclaredFields();
        Class<?> enclosingClass = TestService.class.getEnclosingClass();
        TestService.ReqVo reqVo = JSON.parseObject("{\"a\":\"测试\",\"b\":123}", TestService.ReqVo.class);
        System.out.println(testC0.invoke(new TestService(), reqVo));


    }
}
