package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotations.NotThreadSafe;

/**
 * Created by xuwei19 on 2015/1/13.
 */
@NotThreadSafe
public class ThisEscape {
    public ThisEscape(Event.EventSource eventSource) {
        eventSource.registerListener(new Event.EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething();
            }
        });
    }

    public void doSomething() {
    }
}
