package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotations.ThreadSafe;

/**
 * 使用工厂方法来防止this引用在构造过程中的逸出
 * Created by frodoking on 2015/1/13.
 */
@ThreadSafe
public class SafeListener {
    private final Event.EventListener eventListener;

    private SafeListener() {
        eventListener = new Event.EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
            }
        };
    }

    public static SafeListener newInstance(Event.EventSource eventSource) {
        SafeListener safeListener = new SafeListener();
        eventSource.registerListener(safeListener.eventListener);
        return safeListener;
    }

    public void doSomething() {
    }
}
