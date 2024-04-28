/**
 * @Author: yuancheng yuancheng@mori-matsu.com
 * @Date: 2024-03-15 11:34:03
 * @FilePath: \handwrite_rpc\easy-rpc-core\src\main\java\com\p1nkpeach\easyrpccore\model\ServiceMetaInfo.java
 * @Description: 服务元信息(注册信息)
 */
package com.p1nkpeach.easyrpccore.model;

import com.p1nkpeach.easyrpccore.constant.RpcConstant;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class ServiceMetaInfo {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务版本号
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;
    /**
     * 服务域名
     */
    private String serviceHost;

    /**
     * 服务端口号
     */
    private Integer servicePort;
    /**
     * 服务分组
     */
    private String serviceGroup = "default";
    /**
     * 服务地址
     */
    private String serviceAddress;

    /**
     * @description: 获取服务键名
     * @return {*}
     */
    public String getServiceKey() {
        // 拓展的服务分组
        // return String.format("%s:%s:%s", serviceName, serviceVersion, serviceGroup);
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * @description: 获取服务注册节点键名
     * @return {*}
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    public String getServiceAddress() {
        String searchStr = "http";
        if (!StrUtil.contains(serviceHost, searchStr)) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }

}
