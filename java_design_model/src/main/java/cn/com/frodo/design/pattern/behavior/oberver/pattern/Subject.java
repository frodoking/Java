package cn.com.frodo.design.pattern.behavior.oberver.pattern;

public interface Subject {
    public void attach(Observer obs);

    public void detach(Observer obs);

    public void notifyObserver();
}
