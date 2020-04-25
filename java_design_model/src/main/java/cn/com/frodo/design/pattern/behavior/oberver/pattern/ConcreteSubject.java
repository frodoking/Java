package cn.com.frodo.design.pattern.behavior.oberver.pattern;

import java.util.Enumeration;
import java.util.Vector;

public class ConcreteSubject implements Subject {

    private Vector<Observer> obsVector = new Vector<Observer>();

    @Override
    public void attach(Observer obs) {
        obsVector.add(obs);
    }

    @Override
    public void detach(Observer obs) {
        obsVector.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : obsVector) {
            o.update();
        }
    }

    public Enumeration<Observer> observers() {
        return obsVector.elements();
    }

    public void change() {
        notifyObserver();
    }
}
