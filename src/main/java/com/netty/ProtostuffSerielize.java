package com.netty;

import com.bean.Person;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

public class ProtostuffSerielize {



    public byte[] encode() {

        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        Person person = new Person();
        person.setAddress("广东省深圳市南山区桃源村一期");
        person.setEmail("zhangsan@126.com");
        person.setPhone("1232112414");
        person.setId(1);
        person.setName("zhzangsan");

        Long start = System.currentTimeMillis();
        byte[] bytes = ProtostuffIOUtil.toByteArray(person, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        Long end = System.currentTimeMillis();

        Long time = end - start;
        System.out.println(time);

        Long startJ = System.currentTimeMillis();
        return bytes;
    }
}
