/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 15:34:39
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 16:09:40
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\registry\LocalRegistry.java
 * @Description: 本地注册中心
 */
package com.p1nkpeach.easyrpccore.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegistry {
    /**
     * 注册信息存储
     */
    private static final Map<String, Class<?>> REGISTRY_MAP = new ConcurrentHashMap<>();

    /**
     * 注册服务
     * 
     * @param serviceName
     * @param implClass
     */
    public static void register(String serviceName, Class<?> implClass) {
        REGISTRY_MAP.put(serviceName, implClass);
    }

    /**
     * 获取服务
     * 
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName) {
        return REGISTRY_MAP.get(serviceName);
    }

    /**
     * 删除服务
     * 
     * @param serviceName
     */
    public static void remove(String serviceName) {
        REGISTRY_MAP.remove(serviceName);
    }
}
