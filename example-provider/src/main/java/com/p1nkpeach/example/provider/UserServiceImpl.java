/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:24:45
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-07 16:10:29
 * @FilePath: \handwrite_rpc\example-provider\src\main\java\com\p1nkpeach\example\provider\UserServiceImpl.java
 * @Description: 用户服务实现类
 */
package com.p1nkpeach.example.provider;

import com.p1nkpeach.example.common.model.User;
import com.p1nkpeach.example.common.service.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }

}
