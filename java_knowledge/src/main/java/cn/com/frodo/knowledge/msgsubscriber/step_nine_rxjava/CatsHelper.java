package cn.com.frodo.knowledge.msgsubscriber.step_nine_rxjava;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import com.sun.jndi.toolkit.url.Uri;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Collections;
import java.util.List;

public class CatsHelper {

    ApiWrapper apiWrapper;

    public Observable<Uri> saveTheCutestCat(String query) {
        Observable<List<Cat>> catsListObservable = apiWrapper.queryCats(query);
        Observable<Cat> cutestCatObservable = catsListObservable.map(new Function<List<Cat>, Cat>() {
            @Override
            public Cat apply(List<Cat> cats) throws Exception {
                return findCutest(cats);
            }
        });
        Observable<Uri> storedUriObservable = cutestCatObservable.flatMap(new Function<Cat, Observable<Uri>>() {
            @Override
            public Observable<Uri> apply(Cat cat) throws Exception {
                return apiWrapper.store(cat);
            }
        });
        return storedUriObservable;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
