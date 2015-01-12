package cn.com.frodo.design.pattern.behavior.strategy.demo;

public abstract class DiscountStrategy {
	private double price = 0;
	private int num = 0;

	public DiscountStrategy(double price, int num) {
		this.price = price;
		this.num = num;
	}

	public double getPrice() {
		return price;
	}

	public int getNum() {
		return num;
	}

	public abstract double calculateDiscount();

}
