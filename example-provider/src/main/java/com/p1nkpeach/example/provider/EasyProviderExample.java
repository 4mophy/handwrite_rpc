/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:29:27
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 15:47:27
 * @FilePath: \handwrite_rpc\example-provider\src\main\java\com\p1nkpeach\example\provider\EasyProviderExample.java
 * @Description: 简易服务提供者示例
 */
package com.p1nkpeach.example.provider;

import com.p1nkpeach.easyrpc.registry.LocalRegistry;
import com.p1nkpeach.easyrpc.server.HttpServer;
import com.p1nkpeach.easyrpc.server.VertxHttpServer;
import com.p1nkpeach.example.common.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
