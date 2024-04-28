/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:29:27
 * @FilePath: \handwrite_rpc\example-provider\src\main\java\com\p1nkpeach\example\provider\ProviderExample.java
 * @Description: 服务提供者示例
 */
package com.p1nkpeach.example.provider;

import com.p1nkpeach.easyrpccore.RpcApplication;
import com.p1nkpeach.easyrpccore.config.RegistryConfig;
import com.p1nkpeach.easyrpccore.config.RpcConfig;
import com.p1nkpeach.easyrpccore.model.ServiceMetaInfo;
import com.p1nkpeach.easyrpccore.registry.LocalRegistry;
import com.p1nkpeach.easyrpccore.registry.Registry;
import com.p1nkpeach.easyrpccore.registry.RegistryFactory;
import com.p1nkpeach.easyrpccore.server.HttpServer;
import com.p1nkpeach.easyrpccore.server.VertxHttpServer;
import com.p1nkpeach.example.common.service.UserService;

public class ProviderExample {
    public static void main(String[] args) {
        // Rpc框架初始化
        RpcApplication.init();
        // 注册服务
        // LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        // serviceMetaInfo.setServiceAddress(rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
