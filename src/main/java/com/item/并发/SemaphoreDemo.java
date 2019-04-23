package com.item.并发;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Lee
 * @description 同步器
 * 我们以一个停车场的运作为例。假设停车场只有三个车位，一开始三个车位都是空的。
 * 这时，如果同时来了五辆车，看门人允许其中三辆不受阻碍地进入，然后放下车拦，
 * 剩下的车则必须在停车场外的入口处等待，此后来的车也都不得不在入口处等待。
 * 这时，有一辆车离开停车场，看门人得知后，升起车拦，放入一辆，如果又离开两辆，
 * 则又可以放入两辆，如此往复。在这个场景中，每辆车就好比一个线程，看门人就好比一个信号量，
 * 看门人限制了可以活动的线程。假如里面依然是三个车位，但是看门人改变了规则，要求每次只能停两辆车，那么停车场在进入两辆车后，
 * 其后的车辆就要等到有车离开才能获准许进入。对于Semaphore类而言，就如同一个看门人，限制了可活动的线程数。
 * @date 2019/4/23 17:03
 * @param
 * @return
 **/

public class SemaphoreDemo {

    private static final Semaphore semaphore = new Semaphore(3,true);

    private static final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static class Info extends Thread {
        private final String name;
        private final int age;

        public Info(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":大家好，我是" + name + "我今年" + age + "岁当前时间为：" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(name + "要准备释放许可证了，当前时间为：" + System.currentTimeMillis());
                System.out.println("当前可使用的许可数为：" + semaphore.availablePermits());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] name = {"李明", "王五", "张杰", "王强", "赵二", "李四", "张三"};
        int[] age = {26, 27, 33, 45, 19, 23, 41};
        for (int i = 0; i < 7; i++) {
            Thread t1 = new Info(name[i], age[i]);
            threadPoolExecutor.execute(t1);
        }
    }
}
