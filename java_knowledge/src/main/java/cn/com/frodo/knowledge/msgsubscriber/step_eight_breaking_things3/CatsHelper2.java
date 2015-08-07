package cn.com.frodo.knowledge.msgsubscriber.step_eight_breaking_things3;

import java.util.Collections;
import java.util.List;

import com.sun.jndi.toolkit.url.Uri;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public class CatsHelper2 {

    ApiWrapper apiWrapper;

    /**
     * JDK8
     * @param query
     * @return
     */
    public AsyncJob<Uri> saveTheCutestCat(String query) {
        AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
//        AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(cats -> findCutest(cats));
//        AsyncJob<Uri> storedUriAsyncJob = cutestCatAsyncJob.flatMap(cat -> apiWrapper.store(cat));
//        return storedUriAsyncJob;
        return null;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
