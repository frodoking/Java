package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.ThreadSafe;

import java.util.Vector;

/**
 * 扩展Vector并增加一个“若没有则添加”的方法
 * Created by frodoking on 2015/1/14.
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x){
        boolean absent  = !contains(x);
        if(absent)
            add(x);

        return absent;
    }
}
