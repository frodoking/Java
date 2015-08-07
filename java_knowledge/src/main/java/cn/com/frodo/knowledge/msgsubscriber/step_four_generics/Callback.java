package cn.com.frodo.knowledge.msgsubscriber.step_four_generics;

public interface Callback<T> {
    void onResult(T result);

    void onError(Exception e);
}
