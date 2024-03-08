/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-08 13:42:23
 * @LastEditors: yuancheng yuancheng@mori-matsu.com
 * @LastEditTime: 2024-03-08 13:49:43
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\\utils\ConfigUtils.java
 * @Description: 配置工具类
 */
package com.p1nkpeach.easyrpccore.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

public class ConfigUtils {
    
    /**
     * 加载配置对象
     * 
     * @param <T>
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象(区分环境)
     * 
     * @param <T>
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
