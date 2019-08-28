package com.frodo;

import com.frodo.concurrent.*;
import com.frodo.encodedecode.GsonMultiThreadTest;
import com.frodo.encodedecode.GsonTest;
import com.frodo.gc.GCTest;
import com.frodo.thread.ThreadPoolTest;
import com.frodo.thread.ThreadYieldTest;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/7/27 17:55]
 */
public class Main
{

    public static void main(String[] args)
    {
        Test test = new SemaphoreTest();

        String cmd = args[0];

        //        test = new NIOTest();
        //        test = new SemaphoreTest();
        //        test = new CountDownLatchTest();
        //        test = new SyncTest();
        //        test = new ReentrantLockTest();
        //        test = new QuickSort(new int[] {6, 1, 4, 7, 2, 3});
        if (cmd.equals("gc"))
        {
            test = new GCTest();
        }
        else if (cmd.equals("clq"))
        {
            if (args.length == 5)
            {
                test = new ConcurrentLinkedQueueTest(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]), Long.parseLong(args[4]));
            }
            else
            {
                test = new ConcurrentLinkedQueueTest();
                System.out.println("int producerNum, int producerWaitTime, int consumerNum, long callTime");
            }
        }
        else if (cmd.equals("lbq"))
        {
            if (args.length == 5)
            {
                test = new LinkedBlockingQueueTest(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]), Long.parseLong(args[4]));
            }
            else
            {
                test = new LinkedBlockingQueueTest();
                System.out.println("int producerNum, int producerWaitTime, int consumerNum, long callTime");
            }
        }
        else if (cmd.equals("dis"))
        {
            if (args.length == 5)
            {
                test = new DisruptorTest(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]), Long.parseLong(args[4]));
            }
            else
            {
                test = new DisruptorTest();
                System.out.println("int producerNum, int producerWaitTime, int consumerNum");
            }
        }
        else if (cmd.equals("jct"))
        {
            if (args.length == 5)
            {
                test = new JCToolsTest(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                        Long.parseLong(args[4]));
            }
            else
            {
                test = new JCToolsTest();
                System.out.println("int producerNum, int producerWaitTime, int consumerNum");
            }
        }
        else if (cmd.equals("gson"))
        {
            if (args.length == 2)
            {
                test = new GsonTest(Integer.parseInt(args[1]));
            }
            else
            {
                test = new GsonTest();
            }
        }
        else if (cmd.equals("gsont"))
        {
            if (args.length == 2)
            {
                test = new GsonMultiThreadTest(Integer.parseInt(args[1]));
            }
            else
            {
                test = new GsonMultiThreadTest();
            }
        }
        else if (cmd.equals("tp"))
        {
            test = new ThreadPoolTest();
        }
        else if (cmd.equals("ty"))
        {
            test = new ThreadYieldTest();
        }

        test.doTest();
    }
}
