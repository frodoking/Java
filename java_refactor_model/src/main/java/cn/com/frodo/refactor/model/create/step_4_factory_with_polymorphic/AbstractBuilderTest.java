package cn.com.frodo.refactor.model.create.step_4_factory_with_polymorphic;

import junit.framework.TestCase;

public abstract class AbstractBuilderTest extends TestCase {
	protected OutputBuilder builder;

	protected abstract OutputBuilder createBuilder(String rootName);

	public void testAddAboveRoot() {
		String invalidResult = "<orders>" + "<order>" + "</oder>" + "</orders>" + "<customer>" + "</customer>";

		builder = createBuilder("orders");
		builder.addBelow("order");

		try {
			builder.addAbove("customer");
			fail("expecting java.lang.RuntimeException");
		} catch (Exception e) {
		}
	}
}
