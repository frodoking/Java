package cn.com.frodo.knowledge.msgsubscriber.step_four_generics;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import cn.com.frodo.knowledge.msgsubscriber.step_three_asynchronous2.Api;

import java.net.URI;
import java.util.List;

public class ApiWrapper {
    Api api;

    public void queryCats(String query, final Callback<List<Cat>> catsCallback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                catsCallback.onResult(cats);
            }

            @Override
            public void onQueryFailed(Exception e) {
                catsCallback.onError(e);
            }
        });
    }

    public void store(Cat cat, final Callback<URI> uriCallback) {
        api.store(cat, new Api.StoreCallback() {
            @Override
            public void onCatStored(URI uri) {
                uriCallback.onResult(uri);
            }

            @Override
            public void onStoreFailed(Exception e) {
                uriCallback.onError(e);
            }
        });
    }
}
