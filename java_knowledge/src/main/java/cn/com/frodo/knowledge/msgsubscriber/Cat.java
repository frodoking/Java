package cn.com.frodo.knowledge.msgsubscriber;

public class Cat implements Comparable<Cat> {
    int cuteness;

    @Override
    public int compareTo(Cat another) {
        return Integer.compare(cuteness, another.cuteness);
    }
}
