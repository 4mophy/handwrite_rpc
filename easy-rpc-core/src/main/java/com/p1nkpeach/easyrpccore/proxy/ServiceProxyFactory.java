/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 19:04:08
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-12 14:01:04
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\proxy\ServiceProxyFactory.java
 * @Description: 服务代理工厂（用于创建代理对象）
 */
package com.p1nkpeach.easyrpccore.proxy;

import java.lang.reflect.Proxy;

import com.p1nkpeach.easyrpccore.RpcApplication;

public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * 
     * @param <T>
     * @param serviceClass
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[] { serviceClass },
                new ServiceProxy());
    }

    /**
     * 根据服务类获取 Mock 代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),new Class[] { serviceClass },new MockServiceProxy());
    }
}
