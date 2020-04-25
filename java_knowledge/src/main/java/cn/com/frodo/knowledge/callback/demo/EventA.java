package cn.com.frodo.knowledge.callback.demo;

public class EventA implements Event {

    @Override
    public String happendEvent() {
        return "job A has been finished!";
    }
}
