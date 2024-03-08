/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-08 13:38:28
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-08 13:41:33
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\config\RpcConfig.java
 * @Description: RPC配置类
 */
package com.p1nkpeach.easyrpccore.config;

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
}
