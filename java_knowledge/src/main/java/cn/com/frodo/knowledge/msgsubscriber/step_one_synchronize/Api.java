package cn.com.frodo.knowledge.msgsubscriber.step_one_synchronize;

import java.util.List;

import com.sun.jndi.toolkit.url.Uri;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public interface Api {
    List<Cat> queryCats(String query);

    Uri store(Cat cat);
}
