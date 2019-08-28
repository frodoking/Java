package com.frodo.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/8/12 20:00]
 */
public class ConcurrentLinkedQueueTest extends ConcurrentQueue
{

    ConcurrentLinkedQueue<Call> queue = new ConcurrentLinkedQueue<>();

    public ConcurrentLinkedQueueTest()
    {
        super();
    }

    public ConcurrentLinkedQueueTest(int producerNum, int producerWaitTime, int consumerNum, long callTime)
    {
        super(producerNum, producerWaitTime, consumerNum, callTime);
    }

    @Override Call poll()
    {
        return queue.poll();
    }

    @Override void offer(Call call)
    {
        queue.offer(call);
    }
}
