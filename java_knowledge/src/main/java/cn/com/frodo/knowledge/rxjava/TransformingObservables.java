package cn.com.frodo.knowledge.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransformingObservables {


    public Observable<Integer> flatObservable(Observable<Integer> observable) {
        return observable.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * 2;
            }
        });
    }

    /**
     * 无序
     *
     * @param observable
     * @return
     */
    public Observable<Integer> flatMapObservable(Observable<Integer> observable) {
        return observable.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                final List<Integer> list = new ArrayList<>();
                for (int i = 10; i < 20; i++) {
                    list.add(i);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        });
    }

    /**
     * 有序
     *
     * @param observable
     * @return
     */
    public Observable<Integer> concatMapObservable(Observable<Integer> observable) {
        return observable.concatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                final List<Integer> list = new ArrayList<>();
                for (int i = 10; i < 20; i++) {
                    list.add(i);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        });
    }

//    public Observable<Boolean> groupByObservable(Observable<Integer> observable) {
//         return observable.groupBy(new Function<Integer, Integer>() {
//             @Override
//             public Integer apply(Integer integer) throws Exception {
//                 return integer%2;
//             }
//         });
//    }
}
