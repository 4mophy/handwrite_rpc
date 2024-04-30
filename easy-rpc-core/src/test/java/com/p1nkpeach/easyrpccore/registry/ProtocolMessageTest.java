/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-04-29 13:49:57
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\test\java\com\p1nkpeach\easyrpccore\registry\ProtocolMessageTest.java
 * @Description: 消息编码测试类
 */
package com.p1nkpeach.easyrpccore.registry;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.p1nkpeach.easyrpccore.constant.RpcConstant;
import com.p1nkpeach.easyrpccore.model.RpcRequest;
import com.p1nkpeach.easyrpccore.protocol.ProtocolConstant;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessage;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessageDecoder;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessageEncoder;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessageSerializerEnum;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessageStatusEnum;
import com.p1nkpeach.easyrpccore.protocol.ProtocolMessageTypeEnum;

import cn.hutool.core.util.IdUtil;
import io.vertx.core.buffer.Buffer;

public class ProtocolMessageTest {
    @Test
    public void testEncodeAndDecode() throws IOException {
        // 构造消息
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[] { String.class });
        rpcRequest.setArgs(new Object[] { "aaa", "bbb" });
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);

        Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
        ProtocolMessage<?> message = ProtocolMessageDecoder.decode(encodeBuffer);
        Assert.assertNotNull(message);
    }
}
