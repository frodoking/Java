package cn.com.frodo.knowledge.msgsubscriber.step_two_asynchronous;

import java.util.List;

import java.net.URI;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public interface Api {
    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);

        void onError(Exception e);
    }

    void queryCats(String query, CatsQueryCallback catsQueryCallback);

    URI store(Cat cat);
}
