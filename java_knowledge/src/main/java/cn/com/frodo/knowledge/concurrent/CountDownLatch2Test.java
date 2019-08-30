package com.frodo.concurrent;

import com.frodo.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch2Test implements Test
{

    @Override public void doTest()
    {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++)
        {
            final int index = i;
            new Thread(new Runnable()
            {
                @Override public void run()
                {
                    System.out.println("------------start--->" + index);
                    try
                    {
                        Thread.sleep(100 * index);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                    System.out.println("------------end----->" + index);
                }
            }).start();
        }

        try
        {
            System.out.println("---main---------start--->");
            countDownLatch.await();
            System.out.println("---main---------end----->" );
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
