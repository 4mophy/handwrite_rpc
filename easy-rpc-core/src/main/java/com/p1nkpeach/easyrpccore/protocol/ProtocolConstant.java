/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-29 09:18:41
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\protocol\ProtocolConstant.java
 * @Description: 协议常量
 */
package com.p1nkpeach.easyrpccore.protocol;

public interface ProtocolConstant {
    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}
