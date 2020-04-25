package cn.com.frodo.knowledge.msgsubscriber.step_one_synchronize;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

import java.net.URI;
import java.util.List;

public interface Api {
    List<Cat> queryCats(String query);

    URI store(Cat cat);
}
