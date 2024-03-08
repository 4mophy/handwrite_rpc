/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 18:46:44
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-08 09:24:50
 * @FilePath: \handwrite_rpc\example-consumer\src\main\java\com\p1nkpeach\example\UserServiceProxyImpl.java
 * @Description: 静态代理
 */
package com.p1nkpeach.example;

import com.p1nkpeach.easyrpc.model.RpcRequest;
import com.p1nkpeach.easyrpc.model.RpcResponse;
import com.p1nkpeach.easyrpc.serializer.JdkSerializer;
import com.p1nkpeach.easyrpc.serializer.Serializer;
import com.p1nkpeach.example.common.model.User;
import com.p1nkpeach.example.common.service.UserService;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class UserServiceProxyImpl implements UserService {

    @Override
    public User getUser(User user) {
        Serializer serializer = new JdkSerializer();
        RpcRequest rpcRequest = RpcRequest.builder().serviceName(UserService.class.getName()).methodName("getUser")
                .parameterTypes(new Class[] { User.class }).args(new Object[] { user }).build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bodyBytes).execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
