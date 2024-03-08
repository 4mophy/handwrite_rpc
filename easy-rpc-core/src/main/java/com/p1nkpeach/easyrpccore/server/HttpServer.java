/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 15:13:37
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 15:14:48
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\server\HttpServer.java
 * @Description: HTTP 服务接口
 */
package com.p1nkpeach.easyrpccore.server;

public interface HttpServer {
    /**
     * 启动服务器
     * 
     * @param port
     */
    void doStart(int port);
}
