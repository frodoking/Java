package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotation.UnThreadSafe;

/**
 * Created by xuwei19 on 2015/1/13.
 */
@UnThreadSafe
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
