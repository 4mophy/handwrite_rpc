/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-15 11:22:44
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\registry\Registry.java
 * @Description: 注册中心
 */
package com.p1nkpeach.easyrpccore.registry;

import java.util.List;

import com.p1nkpeach.easyrpccore.config.RegistryConfig;
import com.p1nkpeach.easyrpccore.model.ServiceMetaInfo;

public interface Registry {
    /**
     * 初始化
     * 
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务(服务端)
     * 
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务(服务端)
     * 
     * @param serviceMetaInfo
     */
    void unregister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现
     * 
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> discovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();

    /**
     * 服务心跳
     */
    void heartbeat();

    /**
     * 监听服务(消费端)
     * 
     * @param serviceNodeKey
     */
    void watch(String serviceNodeKey);
}
