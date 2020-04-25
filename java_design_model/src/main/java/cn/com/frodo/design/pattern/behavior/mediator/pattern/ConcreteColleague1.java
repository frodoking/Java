package cn.com.frodo.design.pattern.behavior.mediator.pattern;

public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void action() {
        System.out.println("这是同事1的行动方法");
    }
}
