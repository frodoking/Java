package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.ThreadSafe;

/**
 * Created by xuwei19 on 2015/1/14.
 */
@ThreadSafe
public class SafePoint {
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
