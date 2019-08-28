package com.frodo.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/8/13 11:39]
 */
public class LinkedBlockingQueueTest extends ConcurrentQueue
{

    LinkedBlockingQueue<ConcurrentQueue.Call> queue = new LinkedBlockingQueue<>();

    public LinkedBlockingQueueTest()
    {
        super();
    }

    public LinkedBlockingQueueTest(int producerNum, int producerWaitTime, int consumerNum, long callTime)
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
