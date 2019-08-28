package com.frodo.concurrent;

import com.frodo.Test;

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

    private Object lock = new Object();
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    @Override public void doTest()
    {
        new Thread(new Runnable()
        {
            @Override public void run()
            {
                try
                {
                    System.out.println("------------notify wait--->");
                    Thread.sleep(5000);
                    System.out.println("------------notify start--->");
                    reentrantLock.lock();
                    condition.signalAll();
                    System.out.println("------------notify end--->");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }finally
                {
                    reentrantLock.unlock();
                }
            }
        }).start();
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
                        reentrantLock.lock();
                        condition.await();
                        System.out.println("------------end----->" + index);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }finally
                    {
                        reentrantLock.unlock();
                    }
                }
            }).start();
        }
    }
}
