package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.NotThreadSafe;
import cn.com.frodo.concurrent.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * ListHelder
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of
 * put-if-absent helper methods for List
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
class BadListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /**
     * 当前方法相对于list的其他操作来说并不是原子的，因此无法确保当前方法执行时另外一个线程不会修改链表
     *
     * @param x
     * @return
     */
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}

@ThreadSafe
class GoodListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /**
     * 针对list的加锁，保证对list操作都是使用同一个锁
     *
     * @param x
     * @return
     */
    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
