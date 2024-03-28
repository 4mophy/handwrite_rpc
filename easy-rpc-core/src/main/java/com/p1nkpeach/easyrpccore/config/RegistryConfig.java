/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-15 14:10:27
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\config\RegistryConfig.java
 * @Description: RPC框架注册中心配置类
 */
package com.p1nkpeach.easyrpccore.config;

import lombok.Data;

@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = "etcd";
    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间(毫秒)
     */
    private Long timeout = 10000L;
}