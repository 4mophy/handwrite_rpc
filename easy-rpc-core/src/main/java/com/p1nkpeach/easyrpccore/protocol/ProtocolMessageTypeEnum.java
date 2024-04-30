/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-29 09:34:06
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\protocol\ProtocolMessageTypeEnum.java
 * @Description: 协议消息的类型枚举
 */
package com.p1nkpeach.easyrpccore.protocol;

import lombok.Getter;

@Getter
public enum ProtocolMessageTypeEnum {
    /**
     * 请求消息
     */
    REQUEST(0),
    /**
     * 响应消息
     */
    RESPONSE(1),
    /**
     * 心跳消息
     */
    HEART_BEAT(2),
    /**
     * 其他消息
     */
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum aEnum : ProtocolMessageTypeEnum.values()) {
            if (aEnum.key == key) {
                return aEnum;
            }
        }
        return null;
    }
}
