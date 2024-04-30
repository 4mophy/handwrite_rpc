/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-29 09:23:11
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\protocol\ProtocolMessageStatusEnum.java
 * @Description: 协议消息状态枚举类
 */
package com.p1nkpeach.easyrpccore.protocol;

import lombok.Getter;

@Getter
public enum ProtocolMessageStatusEnum {
    /**
     * 客户端请求成功
     */
    OK("ok", 20),
    /**
     * 客户端请求错误
     */
    BAD_REQUEST("badRequest", 40),
    /**
     * 服务端处理错误
     */
    BAD_RESPONSE("badResponse", 50);

    private final String text;
    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
