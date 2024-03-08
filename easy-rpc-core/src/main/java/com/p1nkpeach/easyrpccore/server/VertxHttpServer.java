/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:54:09
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 18:45:56
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\server\VertxHttpServer.java
 * @Description: 监听指定端口并处理请求
 */
package com.p1nkpeach.easyrpccore.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        // 创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());
        // server.requestHandler(request -> {
        // // 处理请求
        // System.out.println("Recevied request:" + request.method() + " " +
        // request.uri());
        // request.response().putHeader("content-type", "text/plain").end("Hello form
        // Vert.x HTTP server!");
        // });

        // 启动HTTP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.out.println("Failed to start server:" + result.cause());
            }
        });
    }

}
