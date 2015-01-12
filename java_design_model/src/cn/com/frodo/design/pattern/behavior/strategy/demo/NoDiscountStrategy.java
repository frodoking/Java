package cn.com.frodo.design.pattern.behavior.strategy.demo;

public class NoDiscountStrategy extends DiscountStrategy{

	public NoDiscountStrategy(double price, int num) {
		super(price, num);
	}

	@Override
	public double calculateDiscount() {
		return 0;
	}
}
