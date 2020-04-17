package cn.com.frodo.knowledge.encodedecode.pb;

import cn.com.frodo.MockInterface;
import io.protostuff.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolTest implements MockInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolTest.class);

    @Override public void doTest()
    {
        ProtocolEncoder encoder = new ProtocolEncoder();
        ProtocolDecoder decoder = new ProtocolDecoder();

        POJOSE pojo = new POJOSE();
        pojo.key = "haha";
        pojo.key2 = "hahaxxxx";
        pojo.value = "hoho";
        pojo.value2 = "hohoxxxxx";
        pojo.value3 = "hohosssss";
        byte[] bytes = encoder.encode(pojo);
        POJOE pojose = decoder.decode(bytes, POJOE.class);
        LOGGER.info("key {}, value {}", pojose.key + ", " + pojose.key2, pojose.value + ", " + pojose.value2);
    }

    public static class POJO
    {
        @Tag(100)
        String key;
        @Tag(200)
        String value;
    }

    public static class POJOE extends POJO
    {
        @Tag(101)
        String key2;
        @Tag(203)
        String value2;
    }

    public static class POJOS extends POJO
    {
        @Tag(100)
        String key;
        @Tag(200)
        String value;
        @Tag(203)
        String value3;
    }

    public static class POJOSE extends POJOS
    {
        @Tag(value = 101, alias = "aa")
        String key2;
        @Tag(202)
        String value2;
    }
}
