package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.IAlgorithm;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.com.frodo.Arrays.show;

/**
 * 使用“生产者-消费者模式”编写代码实现：线程A随机间隔（10~200ms）按顺序生成1到100的数字（共100个），
 * 放到某个队列中.3个线程B、C、D即时消费这些数据，线程B打印（向控制台）所有被2整除的数，
 * 线程C打印被3整除的数，线程D打印其它数据，要求数字的打印是有序的（从1到100）
 * 限时40分钟，可以使用IDE及第三方类库
 *
 * @author frodoking
 * @ClassName: ABCABC
 * @date 2020/10/11
 */
public class ABCABC2 implements IAlgorithm {

    static class PrintClass implements Runnable {
        private final Queue<Integer> queue;
        //共用一把锁
        private final Lock lock;
        //阻塞当前线程的条件 + 阻塞下一个线程的条件
        private final Condition current;
        private final Condition next;
        private final String name;
        private final int number;
        private volatile boolean running;

        public PrintClass(BlockingQueue<Integer> queue, Lock lock, Condition current, Condition next, String name, int number, boolean running) {
            this.queue = queue;
            this.lock = lock;
            this.current = current;
            this.next = next;
            this.name = name;
            this.number = number;
            this.running = running;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (running) {
                    Integer head = queue.peek();
                    if (head != null) {
                        if (number == 2 || number == 3) {
                            if (head % number == 0) {
                                queue.poll();
                                show("1Thread " + name + " " + number + ", Number " + head, null);
                                next.signal();
                                current.await();
                            }
                        } else {
                            queue.poll();
                            show("1Thread " + name + " " + number + ", Number " + head, null);
                            next.signal();
                            current.await();
                        }
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    @Override
    public void exec() {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        Lock lock = new ReentrantLock();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        Condition d = lock.newCondition();
        new Thread(() -> {
            Random r = new Random();
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(10 + r.nextInt(190));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(i);
                b.signal();
            }
        }).start();
        new Thread(new PrintClass(queue, lock, b, c, "B", 2, true), "线程B").start();
        new Thread(new PrintClass(queue, lock, c, d, "C", 3, true), "线程C").start();
        new Thread(new PrintClass(queue, lock, d, b, "D", 1, true), "线程D").start();
    }
}
