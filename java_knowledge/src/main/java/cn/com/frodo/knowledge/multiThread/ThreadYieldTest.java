package com.frodo.thread;

import com.frodo.Test;

public class ThreadYieldTest implements Test
{
    @Override public void doTest()
    {
        Runnable r = new Runnable() {

            @Override public void run()
            {
                for (int i = 1; i <= 50; i++) {
                    System.out.println("" + Thread.currentThread().getName() + "-----" + i);
                    // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
                    if (i == 30) {
                        Thread.currentThread().yield();
                    }
                }
            }
        };

        new Thread( r, "zhangsan").start();
        new Thread( r, "lisi").start();
    }
}
