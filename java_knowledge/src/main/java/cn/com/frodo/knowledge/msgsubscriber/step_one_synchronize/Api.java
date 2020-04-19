package cn.com.frodo.knowledge.msgsubscriber.step_one_synchronize;

import java.util.List;

import java.net.URI;

import cn.com.frodo.knowledge.msgsubscriber.Cat;

public interface Api {
    List<Cat> queryCats(String query);

    URI store(Cat cat);
}
