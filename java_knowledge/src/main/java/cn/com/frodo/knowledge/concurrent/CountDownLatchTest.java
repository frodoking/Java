package com.frodo.concurrent;

import com.frodo.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/7/30 16:19]
 */
public class CountDownLatchTest implements Test
{

    @Override public void doTest()
    {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++)
        {
            final int index = i;
            new Thread(new Runnable()
            {
                @Override public void run()
                {
                    try
                    {
                        System.out.println("------------start--->" + index);
                        countDownLatch.await();
                        System.out.println("------------end----->" + index);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("---main---------start--->");
        countDownLatch.countDown();
        System.out.println("---main---------end----->" );
    }
}
