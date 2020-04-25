package cn.com.frodo.concurrent.chapter_5;

import cn.com.frodo.concurrent.annotations.GuardedBy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 问题在于：如果某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在进行，那么很可能会重复这个计算
 * <p/>
 * （
 * 解决办法：<p/>
 * 我们希望通过某种方法来表达“线程X计算f（27）”这种情况，这样当另外一个线程查找f（27）时，<p/>
 * 它能够知道高效的方法是等待线程X计算结束，然后再去查询缓存“f（27）的结果是多少？”<p/>
 * <p>
 * FutureTask可以解决这个问题
 * ）
 * Created by frodo on 2015/1/15.
 */
public class Memoizer2<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
