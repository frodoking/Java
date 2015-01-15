package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.NotThreadSafe;

/**
 * Mutable Point class similar to java.awt.Point
 * <p/>
 * Created by xuwei19 on 2015/1/14.
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
