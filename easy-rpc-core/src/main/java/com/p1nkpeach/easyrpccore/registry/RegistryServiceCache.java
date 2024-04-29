/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-28 15:04:29
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\registry\RegistryServiceCache.java
 * @Description: 注册中心服务本地缓存
 */
package com.p1nkpeach.easyrpccore.registry;

import java.util.List;

import com.p1nkpeach.easyrpccore.model.ServiceMetaInfo;

public class RegistryServiceCache {
    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     */
    void write(List<ServiceMetaInfo> serviceMetaInfoList) {
        this.serviceCache = serviceMetaInfoList;
    }

    /**
     * 读缓存
     * 
     * @return
     */
    List<ServiceMetaInfo> read() {
        return this.serviceCache;
    }

    /**
     * 清除缓存
     */
    void clear() {
        this.serviceCache = null;
    }
}
