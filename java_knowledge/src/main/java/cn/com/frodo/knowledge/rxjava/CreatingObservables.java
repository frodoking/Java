package cn.com.frodo.knowledge.rxjava;

import io.reactivex.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CreatingObservables {

    public Observable<Integer> createObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
    }

    public Observable<Integer> justObservable() {
        return Observable.just(1, 2,3);
    }

    public Observable<Integer> emptyObservable() {
        return Observable.empty();
    }

    public Observable<Integer> fromObservable() {
        return Observable.fromArray(1,2,3);
    }

    public Observable<Long> intervalObservable() {
        return Observable.interval(1000L, TimeUnit.HOURS);
    }

    public Observable<Integer> rangeObservable() {
        return Observable.range(1, 10);
    }

    /**
     * do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
     */
    public Observable<Integer> deferObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(Observer<? super Integer> observer) {
                        observer.onNext(1);
                        observer.onComplete();
                    }
                };
            }
        });
    }

}
