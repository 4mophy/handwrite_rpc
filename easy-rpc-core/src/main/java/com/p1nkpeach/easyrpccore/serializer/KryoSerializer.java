/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-14 10:41:44
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\serializer\KryoSerializer.java
 * @Description: Kryo序列化器
 */
package com.p1nkpeach.easyrpccore.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        KRYO_THREAD_LOCAL.get().writeObject(output, obj);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        T result = KRYO_THREAD_LOCAL.get().readObject(input, classType);
        input.close();
        return result;
    }

    /**
     * @description: kryo 线程不安全，使用 ThreadLocal 保证每个线程只有一个 Kryo
     * @return {*}
     */
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        // 设置动态动态序列化和反序列化类，不提前注册所有类（可能有安全问题）
        kryo.setRegistrationRequired(false);
        return kryo;
    });
}
