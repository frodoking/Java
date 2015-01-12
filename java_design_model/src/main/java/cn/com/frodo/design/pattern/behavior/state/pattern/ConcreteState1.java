package cn.com.frodo.design.pattern.behavior.state.pattern;

public class ConcreteState1 extends State {

	@Override
	public void handle() {
		System.out.println("行为1的逻辑处理");
	}
}
