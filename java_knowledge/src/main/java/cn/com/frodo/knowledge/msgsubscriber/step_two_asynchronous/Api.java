package cn.com.frodo.knowledge.msgsubscriber.step_two_asynchronous;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

import java.net.URI;
import java.util.List;

public interface Api {
    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);

        void onError(Exception e);
    }

    void queryCats(String query, CatsQueryCallback catsQueryCallback);

    URI store(Cat cat);
}
