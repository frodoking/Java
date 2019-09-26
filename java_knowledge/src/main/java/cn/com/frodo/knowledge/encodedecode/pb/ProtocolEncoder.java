package com.frodo.encodedecode.pb;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtocolEncoder
{
    byte[] encode(Object object) {
        Schema schema = RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate());
    }
}
