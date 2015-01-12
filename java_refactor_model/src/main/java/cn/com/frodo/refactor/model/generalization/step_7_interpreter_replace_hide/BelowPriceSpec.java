package cn.com.frodo.refactor.model.generalization.step_7_interpreter_replace_hide;

public class BelowPriceSpec extends Spec {

	private float priceThreshold;

	public BelowPriceSpec(float priceThreshold) {
		this.priceThreshold = priceThreshold;
	}

	@Override
	public boolean isSatisfiedBy(Product product) {
		return product.getPrice() < getPriceThreshold();
	}

	public float getPriceThreshold() {
		return priceThreshold;
	}
}
