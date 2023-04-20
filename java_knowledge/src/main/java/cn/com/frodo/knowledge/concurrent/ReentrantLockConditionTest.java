package cn.com.frodo.knowledge.concurrent;

import cn.com.frodo.MockInterface;
import cn.com.frodo.knowledge.encrypt.UnifiedManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author frodoking
 * @ClassName: ReentrantLockConditionTest
 * @date 2022/3/4
 */
public class ReentrantLockConditionTest implements MockInterface {

    private static final Logger log = LoggerFactory.getLogger(ReentrantLockConditionTest.class);

    @SuppressWarnings(value = "unchecked")
    @Override
    public void doTest() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
//                reentrantLock.lock();
                try {
                    condition.await();
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("<< {} : {}",Thread.currentThread().getName(), Thread.currentThread().getState());
//                reentrantLock.unlock();
            }
        }, "Thread-"+0);
        t.start();
        try {
            System.out.println("....1..");
            TimeUnit.SECONDS.sleep(3);
            reentrantLock.lock();
            condition.signal();
            reentrantLock.unlock();
            System.out.println("...2...");

            List a = new ArrayList<>();
            a.add(123);
            a.add("321");
            System.out.println(a);

            new Date();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
