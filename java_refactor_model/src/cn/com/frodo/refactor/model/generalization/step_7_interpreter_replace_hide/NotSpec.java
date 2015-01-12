package cn.com.frodo.refactor.model.generalization.step_7_interpreter_replace_hide;

public class NotSpec extends Spec {

	private Spec specToNegate;

	public NotSpec(Spec specToNegate) {
		this.specToNegate = specToNegate;
	}

	@Override
	public boolean isSatisfiedBy(Product product) {
		return !specToNegate.isSatisfiedBy(product);
	}

}
