package cn.com.frodo.concurrent.chapter_3;

/**
 * Created by xuwei19 on 2015/1/13.
 */
public class Event {
    public interface EventListener {
        void onEvent(Event e);
    }

    public class EventSource {
        public void registerListener(EventListener eventListener) {
        }
    }
}
