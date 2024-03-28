/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 16:11:50
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 16:19:45
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\model\RpcRequest.java
 * @Description: 
 */
package com.p1nkpeach.easyrpccore.model;

import java.io.Serializable;

import com.p1nkpeach.easyrpccore.constant.RpcConstant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 服务版本
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数列表
     */
    private Object[] args;
}
