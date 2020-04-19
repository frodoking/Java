package cn.com.frodo.knowledge.concurrent;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * Description TODO
 * @author frodoking
 * @version [V1, 2019/8/12 21:03]
 */
public class DisruptorTest extends ConcurrentQueue
{
    Disruptor<Call> disruptor;

    public DisruptorTest()
    {
        this(100, 10, 50, 1);
    }

    public DisruptorTest(int producerNum, int producerWaitTime, int consumerNum, long callTime)
    {
        super(producerNum, producerWaitTime, consumerNum, callTime);
        disruptor = new Disruptor<Call>((EventFactory<Call>) () -> new Call(""), 1024 * 1024 * 4, new ThreadFactory()
        {
            int i = 0;

            @Override public Thread newThread(Runnable r)
            {
                return new Thread(r, "simpleThread" + i++);
            }
        }, ProducerType.MULTI, new YieldingWaitStrategy());

        // 处理Event的handler
        EventHandler<Call> handler = (element, sequence, endOfBatch) -> {
            if (avg.get() == 0)
            {
                avg.set(System.nanoTime() - element.timestamp);
            }
            avg.set((avg.get() + (System.nanoTime() - element.timestamp)) / 2);
            if (callTime > 0)
            {
                Thread.sleep(callTime);
            }
            // System.out.println("----------------" + Thread.currentThread().getName());
        };

//        EventHandler<Call>[] handlers = new EventHandler[consumerNum];
//        for (int i = 0; i < consumerNum; i++)
//        {
//            handlers[i] = handler;
//        }
        // 设置EventHandler
//        disruptor.handleEventsWith(handlers);
        // 启动disruptor的线程
        disruptor.start();
    }

    @Override void consume()
    {
    }

    @Override Call poll()
    {
        return null;
    }

    @Override void offer(Call call)
    {
        disruptor.publishEvent((element, sequence) -> {
            // System.out.println("之前的数据" + element.name + "当前的sequence" + sequence);
            element.timestamp = System.nanoTime();
            element.name = "我是第" + sequence + "个";
        });
    }

}
