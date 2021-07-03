package com.library.network

import com.library.network.callback.NetworkRequestEventCallback
import com.library.network.data.NetworkData

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/3 09:11
 * 作用描述：请求封装
 */
object ApiRequest {
    /**
     * Api请求 不处理异常 直接返回数据 需要自己捕获异常
     */
    suspend fun <T> apiRequest(block: suspend () -> NetworkData<T>?): NetworkData<T>? {
        return block.invoke()
    }


    /**
     * api同步请求 处理异常
     */
    suspend fun <T> safeApiRequestAwait(
        callback: NetworkRequestEventCallback,
        api: suspend () -> NetworkData<T>
    ): NetworkData<T> {
        return try {
            api.invoke()
        } catch (e: Exception) {
            NetworkData(500, "请求异常")
        } finally {
            callback.dismissLoading()
        }
    }
}