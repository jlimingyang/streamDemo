package com.demo.item.test;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.demo.item.beans.PresalePriceRule;
import com.demo.item.beans.Test01;
import com.demo.item.beans.Test02;
import com.demo.item.enums.CmsJumpType;
import org.apache.avro.data.Json;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Demo03 {

    private static Supplier<Test01> test01Supplier = Test01::new;
    private static Supplier<Test02> test02Supplier = Test02::new;

    @Test
    public void test01() {
        Test01 test01 = test01Supplier.get();
        Test02 test02 = test02Supplier.get();
        test01.setId(1);
        test01.setName("测试01");
        test01.setCmsJumpType(CmsJumpType.LINK);
        BeanUtils.copyProperties(test01, test02);
        test02.setJumpType(test01.getCmsJumpType());
        System.out.println(test02);
    }

    @Test
    public void test02() {
        System.out.println(getPrintSize(Double.valueOf(1000000000)));
    }

    public static String getPrintSize(Double size) {
        if (size < 1024) {
            return String.format("%.2fB", size);
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            return String.format("%.2fKB", size);
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            return String.format("%.2fMB", size);
        } else {
            size = size * 100 / 1024;
            return String.format("%.2fGB", size);
        }
    }

    @Test
    public void test03() {
        String mobile = "181112291821";
        if (StringUtils.isEmpty(mobile) || mobile.length() != 11) {
            mobile = createMobile();
        }
        System.out.println(mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }


    //随机生成手机号码前3位
    public static String createMobile() {
        int[] mobileStart = {139, 138, 137, 136, 135, 134, 159, 158, 157, 150, 151, 152, 188, 130, 131, 132, 156, 155, 133, 153, 189, 180, 177, 176};
        Random r = new Random();
        ArrayList<Integer> mobileList = new ArrayList<>();
        for (int i = 0; i < mobileStart.length; i++) {
            mobileList.add(mobileStart[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mobileList.get(r.nextInt(mobileList.size())));
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(r.nextInt(10));
        }
        return stringBuilder.toString();
    }


    @Test
    public void test04() {
        Timestamp startDate = new Timestamp(1544716800000L);
        Timestamp endDate = new Timestamp(1545235200000L);
        Timestamp startTime1 = new Timestamp(-25200000L);
        Timestamp endTime1 = new Timestamp(54000000L);
        List<Date> list = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.of(startDate.toLocalDateTime().toLocalDate(), startTime1.toLocalDateTime().toLocalTime());
        LocalDateTime endTime = LocalDateTime.of(endDate.toLocalDateTime().toLocalDate(), endTime1.toLocalDateTime().toLocalTime());
        while (endTime.isAfter(startTime)) {
            Date a = Date.from(startTime.minusMinutes(5).atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(a);
            list.add(a);
            startTime = startTime.plus(1, ChronoUnit.DAYS);
            System.out.println("time++:" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
//        list.stream().forEach(s-> System.out.println(s));
        System.out.println("----------------------------------");
        System.out.println(startDate.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(startDate.toLocalDateTime().minusMinutes(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(startTime.minusMinutes(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Date.from(startDate.toLocalDateTime().minusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println(startTime1.toLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Test
    public void test05() {
        int a = 0;
        try {
            System.out.println(8 / a);
        } catch (Exception e) {
            e.printStackTrace();
            a = 222222222;
        }
        System.out.println(a + 1);
    }

    @Test
    public void test06() {
        List<PresalePriceRule> ladderPriceRules = new ArrayList<PresalePriceRule>() {
            {
                add(new PresalePriceRule(1, 10));
                add(new PresalePriceRule(2, 20));
                add(new PresalePriceRule(2, 30));
                add(new PresalePriceRule(7, 40));
            }
        };

//        int a = 0;
//        ladderPriceRules.forEach(s->{
//            a += s.getLadderPeople();
//        });
//        List<PresaleStages> presaleStages = new ArrayList<>();
//        int fixdPriceCount = 7;
//        int sellPrice = 5;
//        presaleStages.add(new PresaleStages(true, 0, sellPrice));
//        for (int i = 0; i < ladderPriceRules.size(); i++) {
//            PresalePriceRule presalePriceRule = ladderPriceRules.get(i);
//            presaleStages.add(new PresaleStages(false, presalePriceRule.getLadderPeople(), presalePriceRule.getLadderPrice()));
//            if (fixdPriceCount >= presalePriceRule.getLadderPeople()) {
//                if (i == 0) {
//                    presaleStages.set(i, new PresaleStages(false, 0, sellPrice));
//                } else {
//                    PresalePriceRule presalePriceRule1 = ladderPriceRules.get(i - 1);
//                    presaleStages.set(i, new PresaleStages(false, presalePriceRule1.getLadderPeople(), presalePriceRule1.getLadderPrice()));
//                }
//                presaleStages.set(i + 1, new PresaleStages(true, presalePriceRule.getLadderPeople(), presalePriceRule.getLadderPrice()));
//            }
//        }
//        System.out.println(presaleStages);
//        ladderPriceRules.stream().sorted(new Comparator<PresalePriceRule>() {
//            @Override
//            public int compare(PresalePriceRule o1, PresalePriceRule o2) {
//                int a = o2.getLadderPeople().compareTo(o1.getLadderPeople());
//                System.out.println("compare:"+a);
//                return a;
//            }
//        }).collect(Collectors.toList()).forEach(s->System.out.println(s.getLadderPeople()));
//
//          System.out.println("ccccccccc:"+ladderPriceRules.get(0).getLadderPeople().compareTo(ladderPriceRules.get(1).getLadderPeople()));
//        System.out.println("ccccccccc:"+ladderPriceRules.get(1).getLadderPeople().compareTo(ladderPriceRules.get(0).getLadderPeople()));
    }

    @Test
    public void test07() throws IOException {
        String a = DigestUtils.md5Hex(new FileInputStream("C:\\Users\\admin\\Desktop\\1\\1.xlsx"));
        String b = DigestUtils.md5Hex(new FileInputStream("C:\\Users\\admin\\Desktop\\1\\1.xlsx"));
        String c = DigestUtils.md5Hex(new FileInputStream("C:\\Users\\admin\\Desktop\\1\\1.xlsx"));
        String d = DigestUtils.md5Hex(new FileInputStream("C:\\Users\\admin\\Desktop\\1\\2.xlsx"));
        String e = DigestUtils.md5Hex(new FileInputStream("C:\\Users\\admin\\Desktop\\1\\2.xlsx"));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
    }

    @Test
    public void test08() throws IOException {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");
        list1.add("F");
        Random r = new Random();
        int index = r.nextInt(list1.size());
        System.out.println(index+":::"+list1.get(index));
        System.out.println(list1.remove(index));
        System.out.println(Json.toString(list1));
        System.out.println(index+":::"+list1.get(index));
    }

    @Test
    public void test09(){
        List<String> list = new ArrayList<>();
        System.out.println(list == null);

        String a = "400";
        String b = "234";
        System.out.println(Integer.valueOf(a).compareTo(Integer.valueOf(b)));
    }
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    @Test
    public void tes05(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.CHINESE));
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE,Locale.CHINESE));
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW,Locale.CHINESE));
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW_STANDALONE,Locale.CHINESE));
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.CHINESE));
        System.out.println(localDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE,Locale.CHINESE));

    }

    StopWatch s = new StopWatch("耗时统计");
    @Test
    public void test007(){
        s.start("NO:1");
        Employee e1 = new Employee();
        e1.setAge(21);
        e1.setName("张三");
        Employee e5 = new Employee();
        e5.setAge(21);
        e5.setName("张三");
        Employee e6 = new Employee();
        e6.setAge(21);
        e6.setName("李四");
        Employee e2 = new Employee();
        e2.setName("李四");
        e2.setAge(22);
        Employee e3 = new Employee();
        e3.setName("张三");
        e3.setAge(22);
        Employee e4 = new Employee();
        e4.setName("张三");
        e4.setAge(22);
        List<Employee> list = Lists.newArrayList(e1,e2,e3,e4,e5,e6);
//        Map m = Maps.newConcurrentMap();
//        list.stream().filter(s->{
//            return m.putIfAbsent(s.getName(),true) == null;
//        }).forEach(System.out::println);

        s.stop();
        s.start("NO:2");
        s.stop();
        s.prettyPrint();
        System.out.println("总耗时"+s.getTotalTimeSeconds()+"s");
        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(Employee::getName, a -> 1, (a, b) -> a + b));
        System.out.println(new Gson().toJson(collect));
    }

    @Test
    public void test008(){
        LocalTime localTime = LocalTime.parse("02:00");
        System.out.println(localTime.isBefore(LocalTime.MIN));
    }
}
