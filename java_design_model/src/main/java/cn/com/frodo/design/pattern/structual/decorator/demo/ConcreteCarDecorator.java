package cn.com.frodo.design.pattern.structual.decorator.demo;

public class ConcreteCarDecorator extends CarDecorator {

	public ConcreteCarDecorator(Car car) {
		super(car);
	}

	private void paint() {
		System.out.println("给当前car 喷漆为紫色！！！");
	}

	private void setGPS() {
		System.out.println("给当前car 装上GPS！！！");
	}

	@Override
	public void show() {
		super.show();
		this.paint();
		this.setGPS();
	}
}
