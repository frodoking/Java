package cn.com.frodo.knowledge.concurrent;

import org.jctools.queues.MpscUnboundedArrayQueue;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/8/12 21:03]
 */
public class JCToolsTest extends ConcurrentQueue
{

    MpscUnboundedArrayQueue<ConcurrentQueue.Call> queue = new MpscUnboundedArrayQueue<>(1024*16);

    public JCToolsTest()
    {
        super();
    }

    public JCToolsTest(int producerNum, int producerWaitTime, int consumerNum, long callTime)
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
        //System.out.println(queue.size());
    }

}
