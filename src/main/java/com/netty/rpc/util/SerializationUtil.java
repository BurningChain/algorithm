package com.netty.rpc.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    public SerializationUtil() {

    }

    private static <T> Schema<T> getSchema(Class<T> clazz) {

        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }

        return schema;
    }


    public static <T> byte[] serialize(T object) {
        Class<T> clazz = (Class<T>)object.getClass();
        LinkedBuffer allocate = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] bytes = new byte[0];
        try {
            Schema<T> schema = getSchema(clazz);
            bytes = ProtostuffIOUtil.toByteArray(object, schema, allocate);
            return bytes;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            allocate.clear();
        }
        return null;
    }

    public static <T> T deSerialize(byte[] bytes, Class<T> cls) {

        try {
            T message = objenesis.newInstance(cls);
            ProtostuffIOUtil.mergeFrom(bytes, message, getSchema(cls));
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
