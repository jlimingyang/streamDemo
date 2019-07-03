package com.item.test;

import com.alibaba.fastjson.JSON;
import com.item.enums.CmsJumpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;

public class Demo02 {

    private String name;

//    public Demo02(String name) {
//        this.name = name;
//    }

    public Demo02() {
    }

    @Test
    public void test01() {
        int[] nums = {11, 12, 13, -100, 99};
        System.out.println(IntStream.of(nums).parallel().min().getAsInt());
    }

    @Test
    public void test02() {
//        Object runable = (Runnable)()-> System.out.println(123);
//        new Thread(() -> System.out.println("213")).start();
    }

    public void printMoney(Function<Integer, String> format) {
        System.out.println("mytest:" + format.andThen(s -> "测试" + s).apply(1111111111));
    }

    public static void printMoney1(Function<Integer, String> format) {
        System.out.println("mytest:" + format.andThen(s -> "测试" + s).apply(1111111111));
    }

    @Test
    public void test03() {
//        System.out.println(new DecimalFormat("#,###").format(11111111));
        new Demo02().printMoney((i -> new DecimalFormat("#,###").format(i)));
    }

    public String aaa(String a) {
        return a + "bbbb";
    }

    public String aaa222(String a, int b) {
        return a + "bbbb" + b;
    }

    @Test
    public void test04() {
        //断言 返回BOOLEAN
        Predicate<Integer> predicate = i -> i == 3;
        System.out.println(predicate.test(3));

        IntPredicate predicate1 = i -> i == 3;
        System.out.println(predicate1.test(4));

        Consumer<String> consumer = i -> System.out.println(i);
        consumer.accept("1111");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("测试");

        //提供输入
        Consumer<Function<Integer, String>> consumer2 = Demo02::printMoney1;
        consumer2.accept(i -> new DecimalFormat("#,###").format(i));

        //输入和输出相同
        UnaryOperator<String> unaryOperator = new Demo02()::aaa;
        System.out.println(unaryOperator.apply("测试"));

        //多参数
        BiFunction<String, Integer, String> biFunction = new Demo02()::aaa222;
        System.out.println(biFunction.apply("hhhh", 2222));

        //构造对象 调用aaa
        Supplier<Demo02> supplier = Demo02::new;
        System.out.println(supplier.get().aaa("23"));

        //带参数构造
//        Function<String,Demo02> function = Demo02::new;
//        System.out.println(function.apply("q"));

    }

    @Test
    public void test05() {
        //级联
        Function<Integer, Function<Integer, Integer>> integerFunctionFunction = x -> y -> x + y;
        System.out.println(integerFunctionFunction.apply(1).apply(2));

        //科里化 函数标准化 把多函数转为一个
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }

    /*************************Stream  必须有终止操作才会运行************************************/
    @Test
    public void test06() {
        System.out.println(IntStream.of(new int[]{
                1, 2, 3, 4, 5, 6
        }).map(x -> x * 2).sum());
        System.out.println(IntStream.of(new int[]{
                1, 2, 3, 4, 5, 6
        }).map(Demo02::doubleNumber).sum());
    }

    public static int doubleNumber(int i) {
        return i * 2;
    }


    @Test
    public void test07() {
        System.out.println(Arrays.stream(new int[]{1, 2, 3, 4, 5}).parallel().map(x -> 2 * x).sum());
        //创建一个无限流
        new Random().ints().limit(10);
        IntStream.generate(() -> new Random().nextInt()).limit(10);
        IntStream.rangeClosed(1, 10);
    }

    //中间操作
    @Test
    public void test08() {
        String str = "my name is 007";
        Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::print);
        System.out.println("\n------flatMap---------");
        //intStream和longStream不是stream的子类 所以要进行装箱
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> System.out.print((char) i.intValue() + " "));
        System.out.println("\n-------peek--------");
        //peek is middle operator  , but foreach is finnal operator
        Stream.of(str.split(" ")).peek(x -> System.out.println(x)).forEach(x -> System.out.print(""));
        System.out.println("\n-------filter&limit--------");
        new Random().ints().filter(x -> x > 100000 && x < 999999).limit(10).forEach(System.out::println);
    }

    //终止操作
    @Test
    public void test09() {
        String str = "my name is 007";
//        System.out.println("\n-------forEach--------");
//        //并行无序
//        str.chars().parallel().forEach(x -> System.out.print((char) x));
//        System.out.println("\n-------forEachOrdered--------");
//        //并行有序
//        str.chars().parallel().forEachOrdered(x -> System.out.print((char) x));
//        System.out.println("\n-------collectors--------");
//        List list = Stream.of(str.split(" ")).collect(Collectors.toList());
//        list.forEach(System.out::print);
//        System.out.println("\n-------reduce--------");
        //orElse 参数的判断
        System.out.println(Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2).orElse("&"));
        System.out.println(Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2));
//        System.out.println("\n-------max--------");
//        System.out.println(Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length()).get());
//        System.out.println("\n-------anyMatch--------");
//        System.out.println(Stream.of(str.split(" ")).anyMatch(s -> s.equals("name")));
//        System.out.println("\n-------count--------");
//        System.out.println(Stream.of(str.split(" ")).count());
//        System.out.println("\n-------findAny--------");
        for (int i = 0; i < 1000; i++) {
            System.out.print(Stream.of(str.split(" ")).parallel().findAny().get() + " ");
        }
        System.out.println("\n-------findFirst--------");
        System.out.print(Stream.of(str.split(" ")).findFirst().get() + " ");
    }

    //并行流  多次调用并行 串行  以最后一次为准
    //并行使用包ForkJoinPool.commonPool-worker-2
    //默认线程数为CPU个数
    @Test
    public void test10(){
        //更改默认的线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
        IntStream.rangeClosed(1,100)
                .parallel().peek(this::debug).count();
//                .sequential().peek(this::error).count();
//        System.out.println("----------------------");
        //使用自己创建的线程  这里没有输出 因为MAIN线程结束 本线程随之结束
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(()->IntStream.range(1,100).parallel().peek(this::debug).count());
        pool.shutdown();
    }

    public void debug(int i){
        System.out.println("debug:"+Thread.currentThread()+":"+i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void error(int i){
        System.out.println("error:"+i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Data
    class Student{
        private String name;
        private int age;

        public Student() {
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    private List<Student> students = new ArrayList<Student>(){{
        add(new Student("zhangsan",1));
        add(new Student("lisi",2));
        add(new Student("lisi",3));
        add(new Student("wangwu",4));

    }};

    //收集器
    @Test
    public void test11(){
        TreeSet<Integer> ages = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(ages);

        List<Student> s = students.stream().map(Student -> new Student(Student.getName(),Student.getAge())).collect(Collectors.toCollection(ArrayList::new));
        s.forEach(student -> System.out.println(student.getName()));
        //统计
        System.out.println(students.stream().collect(Collectors.summarizingInt(Student::getAge)));
        //分块
        Map<Boolean,List<Student>> genters = students.stream().sorted().collect(Collectors.partitioningBy(std->std.getAge() == 12));
        System.out.println(genters);
        //分组
        Map<String,List<Student>> groupBy = students.stream().collect(Collectors.groupingBy(Student::getName,Collectors.toCollection(ArrayList::new)));
        System.out.println(JSON.toJSONString(groupBy));
        //分组统计
//        Map<String,Long> map = students.stream().collect(Collectors.groupingBy(Student::getName,Collectors.counting()));
        Map<String,Long> map = students.stream().collect(Collectors.groupingBy(Student::getName,reducing(0L, e -> 1L, Long::sum)));

        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test12(){
        Supplier<Student> supplier = Student::new;
        Student student = supplier.get();
        Student student2 = supplier.get();
        System.out.println(student);
        System.out.println(student2);
        student.setAge(123);
        System.out.println(student.getAge());
        System.out.println(student2.getAge());
    }

    //分页操作
    @Test
    public void test13(){
        students.stream().skip(100).limit(100).forEach(System.out::println);
    }

    @Test
    public void test14(){
        Timestamp a = new Timestamp(-21600000);
        System.out.println(a);
        System.out.println(a.toLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        Timestamp b = new Timestamp(System.currentTimeMillis());
//        System.out.println(b.getTime()-a.getTime());


    }

}