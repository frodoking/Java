package cn.com.frodo.refactor.model.generalization.step_3_composite_replace_morebranch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CompositeSpec extends Spec {
	private List<Spec> specs = new ArrayList<Spec>();

	public List<Spec> getSpecs() {
		return Collections.unmodifiableList(specs);
	}

	@Override
	public boolean isSatisfiedBy(Product product) {
		Iterator<Spec> specifications = getSpecs().iterator();
		boolean satisfiesAllSpecs = true;
		while (specifications.hasNext()) {
			Spec productSpec = specifications.next();
			satisfiesAllSpecs &= productSpec.isSatisfiedBy(product);
		}
		return satisfiesAllSpecs;
	}
}
