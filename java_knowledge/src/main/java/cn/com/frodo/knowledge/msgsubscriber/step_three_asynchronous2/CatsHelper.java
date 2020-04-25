package cn.com.frodo.knowledge.msgsubscriber.step_three_asynchronous2;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class CatsHelper {

    public interface CutestCatCallback {
        void onCutestCatSaved(URI uri);

        void onError(Exception e);
    }

    Api api;

    public void saveTheCutestCat(String query, final CutestCatCallback cutestCatCallback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                api.store(cutest, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(URI uri) {
                        cutestCatCallback.onCutestCatSaved(uri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        cutestCatCallback.onError(e);
                    }
                });
            }

            @Override
            public void onQueryFailed(Exception e) {
                cutestCatCallback.onError(e);
            }
        });
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
