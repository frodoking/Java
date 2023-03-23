package cn.com.frodo.algorithm.other;

import cn.com.frodo.algorithm.AlgorithmPoint;
import cn.com.frodo.algorithm.IAlgorithm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.com.frodo.Arrays.show;

/**
 * @author frodoking
 * @ClassName: ABCABC
 * @date 2020/10/11
 */
@Deprecated
@AlgorithmPoint(tag = AlgorithmPoint.Tag.interview,
        difficulty = AlgorithmPoint.Difficulty.medium, company = { AlgorithmPoint.Company.ali},
        category = AlgorithmPoint.Category.thread)
public class ABCABC implements IAlgorithm {

    static class PrintClass implements Runnable {
        //共用一把锁
        private final Lock lock;
        //阻塞当前线程的条件 + 阻塞下一个线程的条件
        private final Condition current;
        private final Condition next;
        //打印的字母
        private final String printWord;

        //为了在控制台好看到效果，我这里打印5轮
        private int count;

        public PrintClass(Lock lock, Condition current, Condition next, String printWord, int count) {
            this.lock = lock;
            this.current = current;
            this.next = next;
            this.printWord = printWord;
            this.count = count;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (count > 0) {
                    current.await();
                    show(printWord, getClass().getName());
                    count--;
                    next.signal();
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
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        new Thread(new PrintClass(lock, a, b, "A", 3), "线程A").start();
        new Thread(new PrintClass(lock, b, c, "B",3), "线程B").start();
        new Thread(new PrintClass(lock, c, a, "C",3), "线程C").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        a.signal();
        lock.unlock();
    }
}
