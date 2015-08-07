package cn.com.frodo.knowledge.msgsubscriber.step_one_synchronize;

import java.util.Collections;
import java.util.List;

import com.sun.jndi.toolkit.url.Uri;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public class CatsHelper {
    Uri someDefaultValue = null/*some default value*/;
    Api api;

    public Uri saveTheCutestCat(String query) {
        try {
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            Uri savedUri = api.store(cutest);
            return savedUri;
        } catch (Exception e) {
            e.printStackTrace();
            return someDefaultValue;
        }
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
