package cn.com.frodo.design.pattern.structual.flyweight.pattern;

public class ConcreteFlyweight implements Flyweight {
	private String intringsicState;

	ConcreteFlyweight(String intringsicState) {
		this.intringsicState = intringsicState;
	}

	@Override
	public void operation(String extringsecState) {
		System.out.println("内部" + intringsicState + "外部" + extringsecState);
	}
}
