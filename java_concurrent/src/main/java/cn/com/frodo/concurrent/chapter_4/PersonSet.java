package cn.com.frodo.concurrent.chapter_4;

import cn.com.frodo.concurrent.annotations.GuardedBy;
import cn.com.frodo.concurrent.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过封闭机制来确保线程安全
 * Created by frodoking on 2015/1/14.
 */
@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    public class Person {
    }
}
