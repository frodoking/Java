package cn.com.frodo.refactor.model.create.step_4_factory_with_polymorphic;

public class XMLBuilderTest extends AbstractBuilderTest {

	@Override
	protected OutputBuilder createBuilder(String rootName) {
		return new XMLBuilder(rootName);
	}

	public void testAddAboveRoot() {
		String invalidResult = "<orders>" + "<order>" + "</oder>" + "</orders>" + "<customer>" + "</customer>";

		builder = createBuilder("orders");
		builder.addBelow("order");

		builder.addAbove("customer");
		fail("expecting java.lang.RuntimeException");
	}

}
