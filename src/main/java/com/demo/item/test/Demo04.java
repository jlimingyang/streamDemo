package com.demo.item.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

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
        Parameter[] parameters = testC0.getParameters();
        Arrays.asList(parameters).stream().forEach(s->{
            System.out.println(s.getName());
            System.out.println(s.getType());
        });
        System.out.println(testC0.invoke(new TestService(), reqVo));

        JSONObject jsonObject = JSON.parseObject("{\"reqVo\":\"测试\",\"b\":123}");
        ArrayList objList = (ArrayList) Arrays.asList(parameters).stream().collect(ArrayList::new, (a, b) -> {
            a.add(this.typeTransformer(jsonObject, b.getType(), b.getName()));
        }, (a, b) -> {
            a.addAll(b);
        });
        System.out.println(JSON.toJSONString(objList));
    }

    @Test
    public void test00A(){
   Object o = "{\"userMobile\":\"18381652106\",\"parentUserId\":3}";
        JSONObject json = JSON.parseObject((String)o);
       String a =  (String)json.get("userMobile");
       Long b = Long.valueOf((Integer) json.get("parentUserId"));
    }

    private <T> Object typeTransformer(JSONObject jo, Class<T> type, String name) {
        if (type.equals(Long.class)) {
            return jo.getLong(name);
        } else if (type.equals(JSONObject.class)) {
            return jo.getJSONObject(name);
        } else if (type.equals(JSONArray.class)) {
            return jo.getJSONArray(name);
        } else {
            return jo.get(name);
        }
    }


}
