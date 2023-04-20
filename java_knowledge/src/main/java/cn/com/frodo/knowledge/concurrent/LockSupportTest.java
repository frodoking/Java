package cn.com.frodo.knowledge.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("...1......" + new Date());
                LockSupport.park(this);
                try {
                    System.out.println("....2....." + new Date() + Thread.interrupted());
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("......3..." + new Date());
                    }
                } catch (Exception e) {
                    System.out.println("....44....." + new Date() + Thread.interrupted());
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        try {
            System.out.println("....21....." + new Date());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("......31..." + new Date());
            t1.interrupt();
            t1.interrupt();
            t1.interrupt();
            LockSupport.unpark(t1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
