package com.frodo.encodedecode.pb;

import com.frodo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolTest implements Test
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
        byte[] bytes = encoder.encode(pojo);
        POJOE pojose = decoder.decode(bytes, POJOE.class);
        LOGGER.info("key {}, value {}", pojose.key + ", " + pojose.key2, pojose.value + ", " + pojose.value2);
    }

    public static class POJO
    {
        String key;
        String value;
    }

    public static class POJOE extends POJO
    {
        String key2;
        String value2;
    }

    public static class POJOS extends POJO
    {
        String key;
        String value;
        String value3;
    }

    public static class POJOSE extends POJOS
    {
        String key2;
        String value2;
    }
}
