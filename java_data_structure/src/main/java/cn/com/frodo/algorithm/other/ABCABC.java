package cn.com.frodo.algorithm.other;

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
        private int count = 5;

        public PrintClass(Lock lock, Condition current, Condition next, String printWord) {
            this.lock = lock;
            this.current = current;
            this.next = next;
            this.printWord = printWord;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (count > 0) {
                    show(printWord, getClass().getName());
                    next.signal();  //唤醒利用下一个条件阻塞的线程
                    count--;
                    //这里不加判断会导致程序停不下来，上篇文章分析过具体原因
                    if (count > 0) {
                        current.await(); //利用当前条件将当前线程阻塞
                    }
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
        try {
            new Thread(new PrintClass(lock, a, b, "A"), "线程A").start();
            TimeUnit.SECONDS.sleep(1); //确保线程A最先执行
            new Thread(new PrintClass(lock, b, c, "B"), "线程B").start();
            TimeUnit.SECONDS.sleep(1); //确保线程B第2个执行
            new Thread(new PrintClass(lock, c, a, "C"), "线程C").start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
