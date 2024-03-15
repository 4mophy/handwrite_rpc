/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-14 10:57:19
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\serializer\Hessian.java
 * @Description: 
 */
package com.p1nkpeach.easyrpccore.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(bos);
        ho.writeObject(object);
        return bos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(bis);
        return (T) hi.readObject(type);
    }

}
