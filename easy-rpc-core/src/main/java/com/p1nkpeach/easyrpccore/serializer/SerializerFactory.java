/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-14 12:46:25
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\serializer\SerializerFactory.java
 * @Description: 序列化器工厂(用于获取序列化器对象)
 */
package com.p1nkpeach.easyrpccore.serializer;

import com.p1nkpeach.easyrpccore.spi.SpiLoader;

public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    // private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();
    // private static final Serializer DEFAULT_SERIALIZER =
    // KEY_SERIALIZER_MAP.get("jdk");

    // /**
    //  * @description: 序列化映射(用于实现单例)
    //  * @return {*}
    //  */
    // private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {
    //     {
    //         put(SerializerKeys.JDK, new JdkSerializer());
    //         put(SerializerKeys.KRYO, new KryoSerializer());
    //         put(SerializerKeys.JSON, new JsonSerializer());
    //         put(SerializerKeys.HESSIAN, new HessianSerializer());
    //     }
    // };

    /**
     * @description: 获取实例
     * @param {String} key
     * @return {*}
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
        // return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
    }
}
