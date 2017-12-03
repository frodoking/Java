package cn.com.frodo.knowledge.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import static cn.com.frodo.Log.i;

public class CombiningObservables {

    public Observable<Integer> zipObservable() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                i("emit 1");
                emitter.onNext(1);
                Thread.sleep(1000);

                i("emit 2");
                emitter.onNext(2);
                Thread.sleep(1000);

                i("emit 3");
                emitter.onNext(3);
                Thread.sleep(1000);

                i("emit 4");
                emitter.onNext(4);
                Thread.sleep(1000);

                i("emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                i("emit A");
                emitter.onNext(1000);
                Thread.sleep(1000);

                i("emit B");
                emitter.onNext(2000);
                Thread.sleep(1000);

                i("emit C");
                emitter.onNext(3000);
                Thread.sleep(1000);

                i("emit xxx complete");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());


        return Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });
    }
//
//    public Observable<Integer> zipObservable(Observable<Integer> observableA, Observable<Integer> observableB) {
//        return Observable.com(observableA, observableA, new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) throws Exception {
//                return integer + integer2;
//            }
//        });
//    }
}
