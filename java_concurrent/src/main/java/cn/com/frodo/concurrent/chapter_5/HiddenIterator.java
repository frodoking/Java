package cn.com.frodo.concurrent.chapter_5;

import cn.com.frodo.concurrent.annotations.GuardedBy;
import cn.com.frodo.concurrent.annotations.NotThreadSafe;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏在字符串连接中的迭代操作(不要这么做)
 * 可以对set使用Collections.synchronizedSet(set)来包装HashSet,并对同步代码进行封装。
 * Created by frodoking on 2015/1/14.
 */
@NotThreadSafe
public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        System.out.println("DEBUG: added ten elements to : " + set);
    }
}
