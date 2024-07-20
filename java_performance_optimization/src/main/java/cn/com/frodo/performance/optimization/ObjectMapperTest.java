package cn.com.frodo.performance.optimization;

import cn.hutool.core.date.StopWatch;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * 10000 * 4
 */
public class ObjectMapperTest {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapperProvider().getObjectMapper();
    }

    public static void main(String[] args) {
        String json = "   {" +
                "     \"name\": \"jdbc\",\n" +
                "     \"taskReferenceName\": \"jdbc_ref\",\n" +
                "     \"inputParameters\": {\n" +
                "       \"integrationName\": \"db-001\",\n" +
                "       \"statement\": \"SELECT * FROM tableName WHERE id=?\",\n" +
                "       \"parameters\": [\n" +
                "         \"${workflow.input.text}\"\n" +
                "       ],\n" +
                "       \"type\": \"SELECT\"\n" +
                "     },\n" +
                "     \"type\": \"JDBC\",\n" +
                "     \"cacheConfig\": {\n" +
                "       \"ttlInSecond\": 3600,\n" +
                "       \"key\": \"key\"\n" +
                "     },\n" +
                "     \"optional\": false\n" +
                "   }";
        StopWatch stopWatch = new StopWatch("1234");
        Object payloadObject = getPayloadObject(json);

        for (int i = 0; i < 40000; i++) {
            stopWatch.start(">>" + i);
            clone(payloadObject);
            stopWatch.stop();
        }

        System.out.println(stopWatch.getTotalTimeMillis());
    }

    private static Object getPayloadObject(String payload) {
        Object payloadObject = null;
        if (payload != null) {
            try {
                payloadObject = objectMapper.readValue(payload, Object.class);
            } catch (Exception e) {
                payloadObject = payload;
            }
        }
        return payloadObject;
    }

    private final static TypeReference<Map<String, Object>> map = new TypeReference<>() {
    };

    private static Map<String, Object> clone(Object inputTemplate) {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(inputTemplate);
            return objectMapper.readValue(bytes, map);
        } catch (IOException e) {
            throw new RuntimeException("Unable to clone input params", e);
        }
    }
}
