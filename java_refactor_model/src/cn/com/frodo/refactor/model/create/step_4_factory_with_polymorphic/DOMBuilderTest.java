package cn.com.frodo.refactor.model.create.step_4_factory_with_polymorphic;

public class DOMBuilderTest extends AbstractBuilderTest {

	@Override
	protected OutputBuilder createBuilder(String rootName) {
		return new DOMBuilder(rootName);
	}

	

}
