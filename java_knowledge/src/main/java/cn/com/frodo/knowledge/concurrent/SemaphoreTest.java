package com.frodo.concurrent;

import com.frodo.Test;

import java.util.concurrent.Semaphore;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/7/27 17:58]
 */
public class SemaphoreTest implements Test
{

    @Override public void doTest()
    {
        final Semaphore sem = new Semaphore(3);

        for (int i = 0; i < 100; i++)
        {

            final int index = i;
            Thread thread = new Thread(new Runnable()
            {
                @Override public void run()
                {
                    try
                    {
                        if (index % 2 == 0)
                        {
                            System.out.println("Thread " + Thread.currentThread().getName() + " start acquire");
                            sem.acquire();
                            System.out.println("Thread " + Thread.currentThread().getName() + " start acquire success");
                        }
                        else
                        {
                            System.out.println("Thread " + Thread.currentThread().getName() + " start release");
                            sem.release();
                            System.out.println("Thread " + Thread.currentThread().getName() + " start release success");
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            thread.setName("Thread#" + index);
            thread.start();
        }
    }
}
