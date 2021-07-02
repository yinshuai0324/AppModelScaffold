package com.library.network.config

import okhttp3.Interceptor

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/2 14:03
 * 作用描述：默认的网络配置
 */
class DefaultNetworkConfig : NetworkConfig {

    override fun isSingleHost(): Boolean {
        return true
    }

    override fun globalApiHost(): String {
        return "https://test.com/api/"
    }

    override fun multipleApiHost(): HashMap<String, String> {
        return hashMapOf()
    }

    override fun isSupportHttps(): Boolean {
        return true
    }

    override fun interceptors(): ArrayList<Interceptor> {
        return arrayListOf()
    }

    override fun connectTimeout(): Long {
        return 10
    }

    override fun readTimeout(): Long {
        return 10
    }

    override fun writeTimeout(): Long {
        return 10
    }
}