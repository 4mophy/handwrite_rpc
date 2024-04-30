/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 19:01:03
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2024-04-30 13:35:01
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\proxy\ServiceProxy.java
 * @Description: 服务代理（JDK动态代理）
 */
package com.p1nkpeach.easyrpccore.proxy;

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
import com.p1nkpeach.easyrpccore.server.tcp.VertxTcpClient;

import cn.hutool.core.collection.CollUtil;


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
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.discovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
            return rpcResponse.getData();
            // // 发送请求
            // try (HttpResponse httpResponse =
            // HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
            // .body(bodyBytes)
            // .execute()) {
            // byte[] result = httpResponse.bodyBytes();
            // // 反序列化
            // RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            // return rpcResponse.getData();
            // }
        } catch (Exception e) {
            throw new RuntimeException("调用失败");
        }
    }

}
