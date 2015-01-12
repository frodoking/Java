package cn.com.frodo.design.pattern.behavior.strategy.demo;

public class ContextClient {
	private DiscountStrategy discountStrategy;

	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public double contextCalDisc() {
		return discountStrategy.calculateDiscount();
	}

	public static void main(String[] args) {
		ContextClient client = new ContextClient();
		
		DiscountStrategy disc1 = new NoDiscountStrategy(48.5, 20);
		client.setDiscountStrategy(disc1);
		System.out.println("  ---------->   "+client.contextCalDisc());
		
		DiscountStrategy disc2 = new FixDiscountStrategy(48.5, 20);
		client.setDiscountStrategy(disc2);
		System.out.println("  ---------->   "+client.contextCalDisc());
		
		DiscountStrategy disc3 = new PercentageDiscountStrategy(48.5, 20);
		client.setDiscountStrategy(disc3);
		System.out.println("  ---------->   "+client.contextCalDisc());
	}
}
