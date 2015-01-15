package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * (如果一个线程setLower（5），而另外一个线程setUpper（4）； 那么当前就为（5,4）。那么这就是一个无效的状态;
 * 由于lower和upper不是彼此独立的，因此NumberRange不能将线程安全性委托给它的线程安全状态变量;
 * <p/>
 * 可以通过加锁机制来维护不变性条件来保持线程安全性。例如使用一个锁来保护lower和upper。
 * )
 * <p/>
 * <p/>
 * 如果一个类由多个独立且线程安全的状态变量组成，并且在所有的操作中都不包含无效状态转换，那么可以将线程安全性委托给底层的状态变量。
 * <p/>
 * volatile 变量规则：仅当一个变量参与到包含其他状态变量的不变性条件时，才可以声明为volatile类型
 * Created by xuwei19 on 2015/1/14.
 */
@NotThreadSafe
public class NumberRange {
    //不变性条件 lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        //注意 -- 不安全的“先检查后执行”
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
    }

    public void setUpper(int i) {
        //注意 -- 不安全的“先检查后执行”
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
    }

    public boolean isInRange(int i) {
        return i >= lower.get() && i <= upper.get();
    }
}
