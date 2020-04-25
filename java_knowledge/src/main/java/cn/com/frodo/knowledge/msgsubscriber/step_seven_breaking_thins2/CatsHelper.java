package cn.com.frodo.knowledge.msgsubscriber.step_seven_breaking_thins2;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import cn.com.frodo.knowledge.msgsubscriber.step_four_generics.Callback;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public AsyncJob<URI> saveTheCutestCat(String query) {
        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });

        AsyncJob<URI> storedUriAsyncJob = new AsyncJob<URI>() {
            @Override
            public void start(final Callback<URI> cutestCatCallback) {
                cutestCatAsyncJob.start(new Callback<Cat>() {
                    @Override
                    public void onResult(Cat cutest) {
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
        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
