/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-08 13:54:37
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\RpcApplication.java
 * @Description: RPC框架应用 相当于holder，存放项目全局用到的变量。双检锁单例模式实现
 */
package com.p1nkpeach.easyrpccore;

import com.p1nkpeach.easyrpccore.config.RegistryConfig;
import com.p1nkpeach.easyrpccore.config.RpcConfig;
import com.p1nkpeach.easyrpccore.constant.RpcConstant;
import com.p1nkpeach.easyrpccore.registry.Registry;
import com.p1nkpeach.easyrpccore.registry.RegistryFactory;
import com.p1nkpeach.easyrpccore.utils.ConfigUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     * 
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 ShutdownHook,JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy)); 
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     * 
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
