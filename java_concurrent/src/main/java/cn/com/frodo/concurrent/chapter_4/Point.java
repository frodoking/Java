package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.Immutable;

/**
 * Immutable Point class used by DelegatingVehicleTracker
 * <p/>
 * Created by frodoking on 2015/1/14.
 */
@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
