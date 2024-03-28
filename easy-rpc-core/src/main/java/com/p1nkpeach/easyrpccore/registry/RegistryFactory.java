/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-15 15:38:16
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\registry\RegistryFactory.java
 * @Description: 注册中心工厂(用于获取注册中心对象)
 */
package com.p1nkpeach.easyrpccore.registry;

import com.p1nkpeach.easyrpccore.spi.SpiLoader;

public class RegistryFactory {
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /** 
     * @description: 获取实例
     * @param {String} key
     * @return {*}
     */    
    public static Registry getInstance(String key){
        return SpiLoader.getInstance(Registry.class, key);
    }

}
