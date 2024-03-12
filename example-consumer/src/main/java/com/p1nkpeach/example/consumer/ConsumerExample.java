/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:54:09
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-12 14:32:20
 * @FilePath: \handwrite_rpc\example-consumer\src\main\java\com\p1nkpeach\example\consumer\ConsumerExample.java
 * @Description: 服务消费者示例
 */
package com.p1nkpeach.example.consumer;

import com.p1nkpeach.easyrpccore.config.RpcConfig;
import com.p1nkpeach.easyrpccore.proxy.ServiceProxyFactory;
import com.p1nkpeach.easyrpccore.utils.ConfigUtils;
import com.p1nkpeach.example.common.model.User;
import com.p1nkpeach.example.common.service.UserService;

public class ConsumerExample {
    public static void main(String[] args) {

        // 读取配置
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
        // // 静态代理
        // UserService userService = new UserServiceProxy();

        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("laosiji");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user is null");
        }
        long number = userService.getNumber();
        System.out.println(number);

    }
}
