/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 19:01:03
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2024-04-28 10:23:33
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\proxy\ServiceProxy.java
 * @Description: 服务代理（JDK动态代理）
 */
package com.p1nkpeach.easyrpccore.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.p1nkpeach.easyrpccore.RpcApplication;
import com.p1nkpeach.easyrpccore.config.RpcConfig;
import com.p1nkpeach.easyrpccore.constant.RpcConstant;
import com.p1nkpeach.easyrpccore.model.RpcRequest;
import com.p1nkpeach.easyrpccore.model.RpcResponse;
import com.p1nkpeach.easyrpccore.model.ServiceMetaInfo;
import com.p1nkpeach.easyrpccore.registry.Registry;
import com.p1nkpeach.easyrpccore.registry.RegistryFactory;
import com.p1nkpeach.easyrpccore.serializer.Serializer;
import com.p1nkpeach.easyrpccore.serializer.SerializerFactory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serviceName = method.getDeclaringClass().getName();

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

            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.discovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 暂时先取第一个
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
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
