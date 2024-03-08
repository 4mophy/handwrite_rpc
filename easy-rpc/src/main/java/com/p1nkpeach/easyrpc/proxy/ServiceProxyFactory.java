/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 19:04:08
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 19:05:29
 * @FilePath: \handwrite_rpc\easy-rpc\src\main\java\com\p1nkpeach\easyrpc\proxy\ServiceProxyFactory.java
 * @Description: 服务代理工厂（用于创建代理对象）
 */
package com.p1nkpeach.easyrpc.proxy;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * 
     * @param <T>
     * @param serviceClass
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[] { serviceClass },
                new ServiceProxy());
    }
}
