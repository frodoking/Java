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
        Test test;

        String cmd = args[0];
        switch (cmd)
        {
            case "gc":
                test = new GCTest();
                break;
            case "clq":
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
                break;
            case "lbq":
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
                break;
            case "dis":
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
                break;
            case "jct":
                if (args.length == 5)
                {
                    test = new JCToolsTest(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]), Long.parseLong(args[4]));
                }
                else
                {
                    test = new JCToolsTest();
                    System.out.println("int producerNum, int producerWaitTime, int consumerNum");
                }
                break;
            case "gson":
                if (args.length == 2)
                {
                    test = new GsonTest(Integer.parseInt(args[1]));
                }
                else
                {
                    test = new GsonTest();
                }
                break;
            case "gsont":
                if (args.length == 2)
                {
                    test = new GsonMultiThreadTest(Integer.parseInt(args[1]));
                }
                else
                {
                    test = new GsonMultiThreadTest();
                }
                break;
            case "tp":
                test = new ThreadPoolTest();
                break;
            case "ty":
                test = new ThreadYieldTest();
                break;
            default:
                test = new SemaphoreTest();
                break;
        }
        test.doTest();
    }
}
