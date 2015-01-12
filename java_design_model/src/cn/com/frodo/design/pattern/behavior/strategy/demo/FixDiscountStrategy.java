package cn.com.frodo.design.pattern.behavior.strategy.demo;

public class FixDiscountStrategy extends DiscountStrategy {

	public FixDiscountStrategy(double price, int num) {
		super(price, num);
	}

	@Override
	public double calculateDiscount() {
		return getNum() * 1;
	}
}
