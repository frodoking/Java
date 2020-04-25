package cn.com.frodo.knowledge.concurrent;

import cn.com.frodo.MockInterface;

import java.util.concurrent.atomic.AtomicLong;

public abstract class ConcurrentQueue implements MockInterface {
    AtomicLong avg = new AtomicLong();

    AtomicLong avgPull = new AtomicLong();

    int producerNum = 100;

    int producerWaitTime = 100;

    int consumerNum = 50;

    long callTime = 0;

    public ConcurrentQueue() {
    }

    public ConcurrentQueue(int producerNum, int producerWaitTime, int consumerNum, long callTime) {
        this.producerNum = producerNum;
        this.producerWaitTime = producerWaitTime;
        this.consumerNum = consumerNum;
        this.callTime = callTime;
    }

    void produce() {
        for (int i = 0; i < producerNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        offer(new Call("xxxxx"));
                        try {
                            Thread.sleep(producerWaitTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    void consume() {
        for (int i = 0; i < consumerNum; i++) {
            new Thread(() -> {
                while (true) {
                    long startTime = System.nanoTime();
                    Call call = poll();
                    long tackTime = System.nanoTime() - startTime;
                    // System.out.println("TackTime: " + tackTime);
                    if (avgPull.get() == 0) {
                        avgPull.set(tackTime);
                    }
                    avgPull.set((avgPull.get() + tackTime) / 2);
                    if (call != null) {
                        try {
                            long startCallTime = System.nanoTime() - call.timestamp;
                            if (avg.get() == 0) {
                                avg.set(startCallTime);
                            }
                            avg.set((avg.get() + startCallTime) / 2);

                            if (callTime > 0) {
                                Thread.sleep(callTime);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void doTest() {
        produce();
        consume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // System.out.println("Avg: " + avg.get() + " Avg Pull: " + avgPull.get() + " Queue Size: " + queue.size());
                    System.out.println("Avg: " + avg.get() + " Avg Pull: " + avgPull.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    abstract Call poll();

    abstract void offer(Call call);

    static class Call {
        long timestamp = System.nanoTime();

        String name;

        public Call(String name) {
            this.name = name;
        }
    }
}
