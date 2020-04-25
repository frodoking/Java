package cn.com.frodo.design.pattern.behavior.mediator.pattern;

public class ConcreteMediator extends Mediator {
    private Colleague c1;
    private Colleague c2;

    @Override
    public void colleagueChanged(Colleague c) {
        c1.action();
        c2.action();
    }

    public void createConcreteMediator() {
        c1 = new ConcreteColleague1(this);
        c2 = new ConcreteColleague2(this);
    }

    public Colleague getC1() {
        return c1;
    }

    public Colleague getC2() {
        return c2;
    }
}
