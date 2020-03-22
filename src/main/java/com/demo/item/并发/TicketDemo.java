package com.demo.item.并发;

public class  TicketDemo extends Thread{
    private  static volatile int index=1;//
    private static final int MAX=50000;
    @Override
    public void run() {
            while(index<=MAX){
                System.out.println(Thread.currentThread().getName()+"叫到号码是："+(index++));
        }
    }

    public static void main(String[] args) {
        TicketDemo t1=new TicketDemo();
        TicketDemo t2=new TicketDemo();
        TicketDemo t3=new TicketDemo();
        TicketDemo t4=new TicketDemo();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

