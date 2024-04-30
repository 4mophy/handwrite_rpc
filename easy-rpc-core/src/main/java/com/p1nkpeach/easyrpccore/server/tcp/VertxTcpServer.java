/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-29 10:28:43
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\server\tcp\VertxTcpServer.java
 * @Description: Vert.x服务器实例
 */
package com.p1nkpeach.easyrpccore.server.tcp;

import com.p1nkpeach.easyrpccore.server.HttpServer;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxTcpServer implements HttpServer {
    private byte[] handleRequest(byte[] requsetData) {
        // 处理请求并返回响应
        // 示例代码，具体根据业务调整
        return "Hello,client!".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建Vert.x服务器实例
        Vertx vertx = Vertx.vertx();

        // 创建TCP服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(socket -> {
            // String testMessage = "Hello, server!Hello, server!Hello, server!Hello,
            // server!";
            // int messageLength = testMessage.getBytes().length;

            // 构造parser
            RecordParser parser = RecordParser.newFixed(8);
            parser.setOutput(new Handler<Buffer>() {
                int size = -1;
                Buffer resultBuffer = Buffer.buffer();

                @Override
                public void handle(Buffer buffer) {
                    if (-1 == size) {
                        size = buffer.getInt(4);
                        parser.fixedSizeMode(size);
                        // 写入头信息到结果
                        resultBuffer.appendBuffer(buffer);
                    } else {
                        // 写入数据到结果
                        resultBuffer.appendBuffer(buffer);
                        System.out.println(resultBuffer.toString());
                        // 重置一轮
                        parser.fixedSizeMode(8);
                        size = -1;
                        resultBuffer = Buffer.buffer();
                    }
                }
            });
            socket.handler(parser);
            // if (buffer.getBytes().length < messageLength) {
            // System.out.println("半包,length="+buffer.getBytes().length);
            // return;
            // }
            // if (buffer.getBytes().length > messageLength) {
            // System.out.println("粘包,length="+buffer.getBytes().length);
            // return;
            // }
            // String str = new String(buffer.getBytes(0,messageLength));
            // System.out.println(str);
            // if (testMessage.equals(str)) {
            // System.out.println("good");
            // }
            // byte[] requestData = buffer.getBytes();
            // byte[] responseData = handleRequest(requestData);
            // socket.write(Buffer.buffer(responseData));
        });

        // 启动TCP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
