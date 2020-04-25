package cn.com.frodo.knowledge.msgsubscriber.step_one_synchronize;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class CatsHelper {
    URI someDefaultValue = null/*some default value*/;
    Api api;

    public URI saveTheCutestCat(String query) {
        try {
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            URI savedUri = api.store(cutest);
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
