package cn.com.frodo.knowledge.encodedecode.pb;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;

public class ProtocolEncoder {
    byte[] encode(Object object) {
        // Schema schema = RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, null, LinkedBuffer.allocate());
    }
}
