package cn.com.frodo.concurrent.chapter_5;

import cn.com.frodo.concurrent.annotations.GuardedBy;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap的高并发性,
 * <p/>
 * 若结果已经计算出来了，那么将立即返回。
 * 如果其他线程正在计算该结果，那么新到的线程将一直等待这个结果被计算出来。
 * 它只有一个缺陷，即仍然存在两个线程计算出相同的漏洞。
 * <p>
 * 问题在于：
 * 复合操作（“若没有则添加”）是在底层的Map对象上执行的，而这个对象无法通过加锁来确保cache原子性。
 * 使用ConcurrentMap中的原子方法putIfAbsent
 * Created by frodo on 2015/1/15.
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();//在这里将调用c.compute
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
