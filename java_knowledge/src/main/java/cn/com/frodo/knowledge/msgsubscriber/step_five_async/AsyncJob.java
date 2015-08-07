package cn.com.frodo.knowledge.msgsubscriber.step_five_async;

import cn.com.frodo.knowledge.msgsubscriber.step_four_generics.Callback;

public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}