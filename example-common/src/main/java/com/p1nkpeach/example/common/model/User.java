/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-07 14:14:26
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-08 10:50:22
 * @FilePath: \handwrite_rpc\example-common\src\main\java\com\p1nkpeach\example\common\model\User.java
 * @Description: 用户类继承可序列化接口
 */
package com.p1nkpeach.example.common.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
