/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-08 13:38:28
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2024-03-14 12:58:30
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\config\RpcConfig.java
 * @Description: RPC配置类
 */
package com.p1nkpeach.easyrpccore.config;

import com.p1nkpeach.easyrpccore.serializer.SerializerKeys;

import lombok.Data;

@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "yu-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;
}
