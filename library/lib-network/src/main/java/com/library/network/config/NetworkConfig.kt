package com.library.network.config

import okhttp3.Interceptor

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/2 13:49
 * 作用描述：网络配置类
 */
interface NetworkConfig {

    /**
     * 是否是使用单个Api域名
     */
    fun isSingleHost(): Boolean

    /**
     * 全局Api Host 如果是单个 则此方法必须返回Host
     */
    fun globalApiHost(): String

    /**
     * 多个Api Host
     */
    fun multipleApiHost(): HashMap<String, String>

    /**
     * 是否支持Https 此处支持Https采用绕过证书的方式 如果需要自己配置Https证书 暂未支持 需要后期增加此功能
     */
    fun isSupportHttps(): Boolean

    /**
     * 拦截器
     */
    fun interceptors(): ArrayList<Interceptor>

    /**
     * 连接超时时间
     * 单位：秒
     */
    fun connectTimeout(): Long

    /**
     * 读取超时时间
     * 单位：秒
     */
    fun readTimeout(): Long

    /**
     * 写入超时时间
     * 单位：秒
     */
    fun writeTimeout(): Long

}