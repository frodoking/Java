package cn.com.frodo.knowledge.juc;

/**
 * Created by frodoking on 2019/3/10 上午11:22.
 * Description:
 */
public class SynchronizedProducerConsumer implements ProducerConsumer {
    public static int count = 0;
    public static final int FULL = 10;
    public static final Object LOCK = new Object();

    @Override
    public void produce() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "produce: " + count);
                    LOCK.notifyAll();
                }
            }
        }).start();
    }

    @Override
    public void consume() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "consume: " + count);
                    LOCK.notifyAll();
                }
            }
        }).start();
    }
}
