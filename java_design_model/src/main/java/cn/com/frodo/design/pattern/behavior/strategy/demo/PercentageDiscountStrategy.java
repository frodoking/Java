package cn.com.frodo.design.pattern.behavior.strategy.demo;

public class PercentageDiscountStrategy extends DiscountStrategy {

    public PercentageDiscountStrategy(double price, int num) {
        super(price, num);
    }

    @Override
    public double calculateDiscount() {
        return getNum() * getPrice() * 0.15;
    }
}
