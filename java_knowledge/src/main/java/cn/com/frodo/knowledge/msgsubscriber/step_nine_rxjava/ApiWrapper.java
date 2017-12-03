package cn.com.frodo.knowledge.msgsubscriber.step_nine_rxjava;

import cn.com.frodo.knowledge.msgsubscriber.Cat;
import cn.com.frodo.knowledge.msgsubscriber.step_three_asynchronous2.Api;
import com.sun.jndi.toolkit.url.Uri;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.List;

public class ApiWrapper {
    Api api;

    public Observable<List<Cat>> queryCats(final String query) {
        return Observable.create(new ObservableOnSubscribe<List<Cat>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Cat>> emitter) throws Exception {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        emitter.onNext(cats);
                    }

                    @Override
                    public void onQueryFailed(Exception e) {
                        emitter.onError(e);
                    }
                });
            }
        });
    }

    public Observable<Uri> store(final Cat cat) {
        return Observable.create(new ObservableOnSubscribe<Uri>() {

            @Override
            public void subscribe(final ObservableEmitter<Uri> emitter) throws Exception {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        emitter.onNext(uri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        emitter.onError(e);
                    }
                });
            }
        });
    }
}
