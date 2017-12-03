package cn.com.frodo.knowledge.rxjava;

import cn.com.frodo.MockInterface;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static cn.com.frodo.Log.i;

public class Rxjava implements MockInterface {

    private Observer<Integer> mockObserver() {
        return new Observer<Integer>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.mDisposable = d;
                i("Rxjava >> %s", "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                i("Rxjava >> %s %s", "onNext", value.toString());
                if (value==2) {
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                i("Rxjava >> %s", "onError");
            }

            @Override
            public void onComplete() {
                i("Rxjava >> %s", "onComplete");
            }
        };
    }



    private <T> Consumer<T> mockConsumer() {
         return new Consumer<T>() {
            @Override
            public void accept(T t) {
                i("Rxjava >> Observer thread is :%s", Thread.currentThread().getName());
                i("Rxjava >> %s %s", "onNext", t.toString());
            }
        };
    }

    @Override
    public void doTest() {
//        new CreatingObservables().createObservable().subscribe(mockConsumer());
//        new CreatingObservables().createObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(mockConsumer());

//        new TransformingObservables().groupByObservable(new CreatingObservables().rangeObservable()).subscribe(mockConsumer());

        new CombiningObservables().zipObservable();
    }
}
