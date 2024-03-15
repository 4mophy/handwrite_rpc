/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 16:22:02
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2024-03-14 13:05:32
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\server\HttpServerHandler.java
 * @Description: 请求处理器
 */
package com.p1nkpeach.easyrpccore.server;

import java.io.IOException;
import java.lang.reflect.Method;

import com.p1nkpeach.easyrpccore.RpcApplication;
import com.p1nkpeach.easyrpccore.model.RpcRequest;
import com.p1nkpeach.easyrpccore.model.RpcResponse;
import com.p1nkpeach.easyrpccore.registry.LocalRegistry;
import com.p1nkpeach.easyrpccore.serializer.Serializer;
import com.p1nkpeach.easyrpccore.serializer.SerializerFactory;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 记录日志
        System.out.println("Received request: " + request.method() + " " + request.uri());

        // 异步处理HTTP请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = (RpcRequest) serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();

            // 如果请求为null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
                
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }

            doResponse(request, rpcResponse, serializer);
        });

    }

    /**
     * 响应
     * 
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }

}
