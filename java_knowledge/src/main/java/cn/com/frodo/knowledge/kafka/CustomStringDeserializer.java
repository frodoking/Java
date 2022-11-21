package cn.com.frodo.knowledge.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CustomStringDeserializer extends StringDeserializer {
    private String encoding = StandardCharsets.UTF_8.name();
    @Override
    public String deserialize(String topic, byte[] data) {
        try {
            String result = new String(data, encoding);
            if (result.equals("value_1668916591919")) {
                System.out.println("topic = " + topic + ", data = " + result);
                throw new UnsupportedOperationException("");
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return super.deserialize(topic, data);
    }
}
