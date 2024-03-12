/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:29:27
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-12 14:12:04
 * @FilePath: \handwrite_rpc\example-provider\src\main\java\com\p1nkpeach\example\provider\ProviderExample.java
 * @Description: 服务提供者示例
 */
package com.p1nkpeach.example.provider;

import com.p1nkpeach.easyrpccore.RpcApplication;
import com.p1nkpeach.easyrpccore.registry.LocalRegistry;
import com.p1nkpeach.easyrpccore.server.HttpServer;
import com.p1nkpeach.easyrpccore.server.VertxHttpServer;
import com.p1nkpeach.example.common.service.UserService;

public class ProviderExample {
    public static void main(String[] args) {
        // Rpc框架初始化
        RpcApplication.init();
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
