package cn.com.frodo.knowledge.msgsubscriber.step_three_asynchronous2;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

import java.net.URI;
import java.util.List;

public interface Api {
    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);

        void onQueryFailed(Exception e);
    }

    interface StoreCallback {
        void onCatStored(URI uri);

        void onStoreFailed(Exception e);
    }


    void queryCats(String query, CatsQueryCallback catsQueryCallback);

    void store(Cat cat, StoreCallback storeCallback);
}
