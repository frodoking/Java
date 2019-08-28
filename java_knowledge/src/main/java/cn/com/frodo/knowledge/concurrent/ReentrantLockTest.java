package com.frodo.concurrent;

import com.frodo.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/8/1 16:03]
 */
public class ReentrantLockTest implements Test
{
    static final int SIZE = 10;

    static volatile int size = 0;

    static ReentrantLock lock = new ReentrantLock();

    static Condition notFull = lock.newCondition();

    static Condition notEmpty = lock.newCondition();

    public static class Producer
    {
        void produce()
        {
            lock.lock();
            if (size == SIZE)
            {
                try
                {
                    notFull.await();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            size++;
            System.out.println(Thread.currentThread().getName() + " produce one, current size: " + size);
            notEmpty.signal();

            lock.unlock();
        }
    }

    public static class Consumer
    {
        void consume()
        {
            lock.lock();
            if (size == 0)
            {
                try
                {
                    notEmpty.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            size--;
            System.out.println(Thread.currentThread().getName() + " consume one, current size: " + size);
            notFull.signal();
            lock.unlock();
        }
    }

    @Override public void doTest()
    {
        final Producer producer = new  Producer();
        final  Consumer consumer = new  Consumer();
        for (int i = 0; i < SIZE; i++)
        {
            new Thread(new Runnable()
            {
                @Override public void run()
                {
                    producer.produce();
                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable()
            {
                @Override public void run()
                {
                    consumer.consume();
                    try
                    {
                        Thread.sleep(90);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
