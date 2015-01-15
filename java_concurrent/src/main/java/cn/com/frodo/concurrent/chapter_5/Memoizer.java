package cn.com.frodo.concurrent.chapter_5;

import cn.com.frodo.concurrent.annotations.GuardedBy;

import java.util.concurrent.*;

/**
 *
 * 当缓冲的是Future而不是值时，将导致缓存污染（Cache Pollution）问题：
 * 如果某个计算被取消或者失败，那么在计算这个结果时将致命计算过程被取消或者失败。
 * 为了避免这种情况，如果Memoizer发现计算被取消，那么将Future从缓存中移除。
 * 如果检查到RuntimeException，那么也会移除Future，这样将来的计算才可能成功。
 * Memoizer同样没有解决缓存逾期的元素。
 * （同样，它也没有解决缓存清理的问题，即移除旧的计算结果以便为新的计算结果腾出空间，从而使缓存不会消耗过多的内存。）
 * Created by frodo on 2015/1/15.
 */
public class Memoizer<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                // 使用ConcurrentMap中的原子方法putIfAbsent
                f = cache.putIfAbsent(arg, ft);
                cache.put(arg, ft);
                ft.run();//在这里将调用c.compute
            }
            try {
                return f.get();
            } catch (CancellationException e){
                cache.remove(arg,f);
            } catch (ExecutionException e1) {
                throw LaunderThrowable.launderThrowable(e1.getCause());
            }
        }
    }
}
