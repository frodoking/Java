package cn.com.frodo.knowledge;

import cn.com.frodo.MockInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicObjectTest implements MockInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicObjectTest.class);

    @Override
    public void doTest() {
        Integer a = 100;
        Integer b = 100;
        LOGGER.info("Integer 100==100 ? {}", a == b);
        a = 128;
        b = 128;
        LOGGER.info("Integer 100==100 ? {}", a == b);
    }
}
