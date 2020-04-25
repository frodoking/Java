package cn.com.frodo.refactor.model.create.step_4_factory_with_polymorphic;


import cn.com.frodo.MockInterface;

import static org.junit.Assert.fail;

public abstract class AbstractBuilderTest implements MockInterface {
    protected OutputBuilder builder;

    protected abstract OutputBuilder createBuilder(String rootName);

    @Override
    public void doTest() {
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
