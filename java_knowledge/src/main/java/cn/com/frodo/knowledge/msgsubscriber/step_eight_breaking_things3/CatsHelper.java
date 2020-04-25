package cn.com.frodo.knowledge.msgsubscriber.step_eight_breaking_things3;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import cn.com.frodo.knowledge.msgsubscriber.step_seven_breaking_thins2.Func;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public AsyncJob<URI> saveTheCutestCat(String query) {
        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });

        AsyncJob<URI> storedUriAsyncJob = cutestCatAsyncJob.flatMap(new Func<Cat, AsyncJob<URI>>() {
            @Override
            public AsyncJob<URI> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });
        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
