/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 15:50:16
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 15:56:10
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\serializer\Serializer.java
 * @Description: 序列化器接口
 */
package com.p1nkpeach.easyrpccore.serializer;

import java.io.IOException;

public interface Serializer {
    /**
     * 序列化
     * 
     * @param <T>
     * @param object
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * 
     * @param <T>
     * @param bytes
     * @param type
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
