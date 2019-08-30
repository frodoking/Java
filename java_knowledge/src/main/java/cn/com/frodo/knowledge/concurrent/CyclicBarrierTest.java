package com.frodo.concurrent;

import com.frodo.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest implements Test
{
    @Override public void doTest()
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            try
            {
                System.out.println("---barrier---------start--->");
                Thread.sleep(1000);
                System.out.println("---barrier---------end----->");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 20; i++)
        {
            final int index = i;
            new Thread(() -> {
                System.out.println("------------start--->" + index);
                try
                {
                    Thread.sleep(100 * index);
                    cyclicBarrier.await();
                }
                catch (InterruptedException | BrokenBarrierException e)
                {
                    e.printStackTrace();
                }
                System.out.println("------------end----->" + index);
            }).start();
        }

    }
}
