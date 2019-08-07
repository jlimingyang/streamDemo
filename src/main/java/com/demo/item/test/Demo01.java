package com.demo.item.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Demo01 {

    private static <T> T test01(T arg,T arg2){
        log.info("arg={}",arg);
        log.info("arg2={}",arg2);
        return arg;
    }


    @AllArgsConstructor
    @Data
    class Box<T> {
        private T data;
    }

    public  void getData(Box<?> box){
        log.info("res={}",box.getData());
    }

    @Data
    class Box01<T> extends Box<T>{
        public Box01(T data) {
            super(data);
        }

        private T a;
    }

    @Test
    public void test01(){
        System.out.println(test01(1, 2));
        Box<String> box01 = new Box<>("1");
        getData(box01);
        Box01 box011 = new Box01("1");
        System.out.println( box01 instanceof Box);
        System.out.println( Box.class.isInstance(box01));
    }


    @Test
    public void test02(){
        List<Human> list = new ArrayList<Human>(){
            {
                add(new Human(9,"999"));
                add(new Human(10,"1111"));
                add(new Human(8,"8888"));
                add(new Human(6,"66666"));
            }
        };
//        list = list.stream().sorted(new Comparator<Human>() {
//            @Override
//            public int compare(Human o1, Human o2) {
//                int a =  o1.age > o2.age ? 1 : -1;
//                log.info("++++++++++____---------:{}",a);
//                return a;
//            }
//        }).collect(Collectors.toList());
        list.sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.age.compareTo(o2.age);
            }
        });
        list.forEach((a)->log.info("age:{}",a.getAge()));

    }

    @Data
    @AllArgsConstructor
    class Human{
        private Integer age;
        private String name;
    }

    @Test
    public void test03(){
        List<Human> list = new ArrayList<Human>(){{
            add(new Human(1,"a"));
            add(new Human(2,"b"));
            add(new Human(3,"c"));
            add(new Human(4,"d"));
        }};
        List<Human> list2 = new ArrayList<Human>(){{
            add(new Human(11,"e"));
            add(new Human(23,"f"));
            add(new Human(34,"g"));
            add(new Human(4,"h"));
        }};
//        list.stream().parallel().peek(s->{
//            System.out.println("1--"+Thread.currentThread());
//            list2.stream().parallel().forEach(x->{
//                System.out.println("2--"+Thread.currentThread());
//                if(s.getAge().equals(x.getAge())){
//                    s.setName("12312");
//                }
//            });
//        }).count();
//        System.out.println("---------------------");
//        list.forEach(System.out::println);

//        list.stream().sorted((s1,s2)->s2.getAge().compareTo(s1.getAge())).collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
//        list.set(0,new Human(77,"ceshi"));
//        list.forEach(System.out::println);
//        System.out.println(list2.stream().mapToInt(s -> s.getAge()).min().getAsInt());
//        List<Human> linkedList = new ArrayList<>(list2);
//        List<Human> list1 = new ArrayList<>();
//        linkedList.addAll(0,list);
//        linkedList.addAll(0,list1);
//        linkedList.addAll(linkedList.size()-1,list);
//        System.out.println(linkedList);
        list2.stream().sorted(Comparator.comparing(Human::getAge)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void test04(){
        LocalDateTime localDateTime = new Date().toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日 HH:mm:ss开启");
        System.out.printf( localDateTime.format(formatter));
    }

    @Test
    public void test05(){
//        String date = "2018-12-05";
//        String time = "09:00:00";
//        LocalDate Date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalTime Time = LocalTime.parse(time,DateTimeFormatter.ofPattern("HH:mm:ss"));
//        LocalDateTime dateTime = LocalDateTime.of(Date,Time);
//        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("YYYY年MM月dd日 HH:mm:ss")));

        LocalDateTime localDateTime = LocalDateTime.of(2018,9,8,14,02,03);
        localDateTime.plus(1, ChronoUnit.DAYS);
        localDateTime.plus(1, ChronoUnit.DAYS);
        localDateTime.plus(1, ChronoUnit.DAYS);
        localDateTime = localDateTime.plus(1, ChronoUnit.DAYS);
    }

    @Test
    public void test06(){
        String a = " {\"code\":0,\"message\":\"success\",\"data\":{\"id\":602252990971183104,\"mchId\":\"0\",\"mchOrderNo\":\"BOSS155261894027600013937\",\"paymentSdkParametersJson\":\"{\\\"mchId\\\":\\\"0\\\",\\\"orderId\\\":\\\"BOSS155261894027600013937\\\",\\\"appid\\\":\\\"wxa4eab8e7a92a1ce5\\\",\\\"partnerid\\\":\\\"1491909032\\\",\\\"prepayid\\\":\\\"wx1511022438389915d38d41583978775286\\\",\\\"noncestr\\\":\\\"TAjYb0POHkKbrxTi\\\",\\\"timestamp\\\":\\\"1552618944\\\",\\\"sign\\\":\\\"0175478C3A562E7A9805BE9F54951193\\\",\\\"package\\\":\\\"Sign=WXPay\\\"}\",\"payInfo\":null,\"lineOfCreditPay\":null}}\n";
        JSONObject jb = (JSONObject)JSON.parseObject(a).get("data");
        String b = jb.get("paymentSdkParametersJson").toString();
        System.out.println(b);
    }

}
