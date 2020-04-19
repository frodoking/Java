package cn.com.frodo.knowledge.msgsubscriber.step_five_async;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import cn.com.frodo.knowledge.msgsubscriber.step_four_generics.Callback;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public AsyncJob<URI> saveTheCutestCat(final String query) {
        return new AsyncJob<URI>() {
            @Override
            public void start(final Callback<URI> cutestCatCallback) {
                apiWrapper.queryCats(query)
                        .start(new Callback<List<Cat>>() {
                            @Override
                            public void onResult(List<Cat> cats) {
                                Cat cutest = findCutest(cats);
                                apiWrapper.store(cutest)
                                        .start(new Callback<URI>() {
                                            @Override
                                            public void onResult(URI result) {
                                                cutestCatCallback.onResult(result);
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                cutestCatCallback.onError(e);
                                            }
                                        });
                            }

                            @Override
                            public void onError(Exception e) {
                                cutestCatCallback.onError(e);
                            }
                        });
            }
        };
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}