package cn.com.frodo.knowledge.msgsubscriber.step_nine_rxjava;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import java.net.URI;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public Observable<URI> saveTheCutestCat(String query) {
        Observable<List<Cat>> catsListObservable = apiWrapper.queryCats(query);
        Observable<Cat> cutestCatObservable = catsListObservable.map(cats -> findCutest(cats));
        Observable<URI> storedUriObservable = cutestCatObservable.flatMap((Function<Cat, Observable<URI>>) cat -> apiWrapper.store(cat));
        return storedUriObservable;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
