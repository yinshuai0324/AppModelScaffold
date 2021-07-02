package com.library.network

import android.text.TextUtils
import com.library.network.config.DefaultNetworkConfig
import com.library.network.config.NetworkConfig
import com.library.network.ssl.SSLManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/2 09:09
 * 作用描述：网络全局管理
 */
object NetworkManage {

    /**
     * 网络配置项
     */
    private lateinit var configBean: NetworkConfig

    /**
     * okHttp客户端
     */
    private  lateinit var okHttpClient: OkHttpClient

    /**
     * retrofit实例
     */
    private var retrofitInstance: HashMap<String, Retrofit> = hashMapOf()

    /**
     * 全局的Host key
     */
    const val GLOBAL_API_KEY = "GlobalApiKey"


    /**
     * 配置网络
     */
    fun config(config: NetworkConfig = DefaultNetworkConfig()): NetworkManage {
        this.configBean = config
        return this
    }


    /**
     * 开始配置
     */
    fun build() {
        if (::configBean.isInitialized) {
            //获取Builder
            val okHttpClientBuilder = if (configBean.isSupportHttps()) {
                SSLManager.getSSLSocketFactory()
            } else {
                OkHttpClient.Builder()
            }
            //配置相关
            //添加拦截器
            configBean.interceptors().forEach {
                okHttpClientBuilder.addInterceptor(it)
            }
            //配置连接超时时间
            okHttpClientBuilder.connectTimeout(configBean.connectTimeout(), TimeUnit.SECONDS)
            //读取超时时间
            okHttpClientBuilder.readTimeout(configBean.readTimeout(), TimeUnit.SECONDS)
            //写入超时时间
            okHttpClientBuilder.writeTimeout(configBean.writeTimeout(), TimeUnit.SECONDS)
            okHttpClient = okHttpClientBuilder.build()
            retrofitInstance.clear()
            if (configBean.isSingleHost()) {
                retrofitInstance[GLOBAL_API_KEY] = getRetrofitInstance(configBean.globalApiHost())
            } else {
                if (!TextUtils.isEmpty(configBean.globalApiHost())) {
                    retrofitInstance[GLOBAL_API_KEY] =
                        getRetrofitInstance(configBean.globalApiHost())
                }
                //添加多个Host对应的实例
                configBean.multipleApiHost().forEach {
                    retrofitInstance[it.key] = getRetrofitInstance(it.value)
                }
            }
        } else {
            throw NullPointerException("NetworkManage configBean Not configured")
        }
    }


    /**
     * 根据Host 创建ApiService
     */
    fun <T> createApiService(key: String = GLOBAL_API_KEY, clazz: Class<T>): T {
        if (retrofitInstance.containsKey(key)) {
            return retrofitInstance[key]!!.create(clazz)
        } else {
            throw NullPointerException("The incoming Host does not exist")
        }
    }


    /**
     * 获取Retrofit实例
     */
    private fun getRetrofitInstance(host: String): Retrofit {
        val instance = Retrofit.Builder()
        instance.baseUrl(host)
        instance.addConverterFactory(MoshiConverterFactory.create())
        instance.client(okHttpClient)
        instance.build()
        return instance.build()
    }
}