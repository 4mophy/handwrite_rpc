/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-12 13:04:08
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-12 14:03:23
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\proxy\MockServiceProxy.java
 * @Description: 服务代理工厂（用于创建代理对象）
 */
package com.p1nkpeach.easyrpccore.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
public class MockServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnType = method.getReturnType();
        log.info("Mock invoke {}",method.getName());
        return getDefaultObject(methodReturnType);
    }

    private Object getDefaultObject(Class<?> type){
        //基本类型
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            }else if (type == short.class) {
                return (short) 0;
            }else if (type == int.class) {
                return (int) 0;
            }else if (type == long.class) {
                return (long) 0;
            }
        }
        //对象类型
        return null;
    }
}
