package cn.com.frodo.design.pattern.structual.decorator.demo;

public abstract class CarDecorator implements Car {
	private Car car = null;

	public CarDecorator(Car car) {
		this.car = car;
	}

	@Override
	public void show() {
		this.car.show();
	}
}
