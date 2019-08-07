package com.demo.item.学习笔记;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 多线程 {

    @Test
    public void test01() throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println(1);
        });
        Thread t2 = new Thread(()->{
            System.out.println(2);
        });

        Thread t3 = new Thread(()->{
            System.out.println(3);
        });
        Thread t4 = new Thread(()->{
            System.out.println(4);
        });
        t1.setName("1");
        t2.setName("2");
        t3.setName("3");
        t4.setName("4");

        t1.run();
        t1.join();
        t2.run();
        t3.run();
        t4.run();
    }

    //FIFO first in first out  先入先出队列
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Test
    public void test02(){
        executorService.submit(()->{
            System.out.println(2);
        });
    }

}
