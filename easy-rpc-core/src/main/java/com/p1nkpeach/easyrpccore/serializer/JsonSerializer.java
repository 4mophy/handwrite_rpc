/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-14 09:16:33
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\serializer\JsonSerializer.java
 * @Description: Json序列化器
 */
package com.p1nkpeach.easyrpccore.serializer;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p1nkpeach.easyrpccore.model.RpcRequest;
import com.p1nkpeach.easyrpccore.model.RpcResponse;

public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, classType);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, classType);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, classType);
        }
        return obj;
    }

    /** 
     * @description: 由于 Object 的原始对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     * @param {RpcRequest} rpcRequest
     * @param {Class<T>} type
     * @return {*}
     */    
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            // 如果类型不一致，重新处理类型
            if (!clazz.isAssignableFrom(args[i].getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes, clazz);
            }
        }
        return type.cast(rpcRequest);
    }

    /** 
     * 由于 Object 的原始对象会被擦除，导致反序列化时会被作为 LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     * @param {RpcResponse} rpcResponse
     * @param {Class<T>} type
     * @return {*}
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}