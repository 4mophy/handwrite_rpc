/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 19:01:03
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2024-03-14 14:28:38
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\proxy\ServiceProxy.java
 * @Description: 服务代理（JDK动态代理）
 */
package com.p1nkpeach.easyrpccore.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.p1nkpeach.easyrpccore.RpcApplication;
import com.p1nkpeach.easyrpccore.model.RpcRequest;
import com.p1nkpeach.easyrpccore.model.RpcResponse;
import com.p1nkpeach.easyrpccore.serializer.Serializer;
import com.p1nkpeach.easyrpccore.serializer.SerializerFactory;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            // todo 注意，这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
