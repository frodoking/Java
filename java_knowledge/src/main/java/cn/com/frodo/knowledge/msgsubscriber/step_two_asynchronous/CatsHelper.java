package cn.com.frodo.knowledge.msgsubscriber.step_two_asynchronous;

import java.util.Collections;
import java.util.List;

import com.sun.jndi.toolkit.url.Uri;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public class CatsHelper {

    public interface CutestCatCallback {
        void onCutestCatSaved(Uri uri);
        void onQueryFailed(Exception e);
    }

    Api api;

    public void saveTheCutestCat(String query, final CutestCatCallback cutestCatCallback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                Uri savedUri = api.store(cutest);
                cutestCatCallback.onCutestCatSaved(savedUri);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onQueryFailed(e);
            }

        });
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
