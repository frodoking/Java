package cn.com.frodo.design.pattern.behavior.mediator.pattern;

public class ConcreteColleague2 extends Colleague {

	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
	}

	public void action() {
		System.out.println("这是同事2的行动方法");
	}
}
