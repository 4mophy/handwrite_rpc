/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:14:26
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-12 15:22:46
 * @FilePath: \handwrite_rpc\example-common\src\main\java\com\p1nkpeach\example\common\service\UserService.java
 * @Description: 用户服务
 */
package com.p1nkpeach.example.common.service;

import com.p1nkpeach.example.common.model.User;

public interface UserService {
    /**
     * 获取用户
     * 
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 获取数字
     * 
     * @return
     */
    default short getNumber() {
        return 1;
    }
}
