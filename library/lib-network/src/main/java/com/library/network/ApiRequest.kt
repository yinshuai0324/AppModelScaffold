package com.library.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.network.callback.NetworkRequestEventCallback
import com.library.network.code.HttpCode
import com.library.network.data.NetworkData
import com.library.network.dsl.NetworkRequestDsl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

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
            callback.onDismissLoading()
        }
    }


    /**
     * Api请求 安全调用 处理好请求时可能发生的异常
     */
    suspend fun <T> safeApiRequest(
        viewModel: ViewModel,
        callback: NetworkRequestEventCallback,
        dsl: NetworkRequestDsl<T>.() -> Unit
    ) {
        val retrofitCoroutine = NetworkRequestDsl<T>()
        retrofitCoroutine.dsl()
        //显示加载框
        retrofitCoroutine.onLoading?.invoke()

        //使用异步流进行处理
        flow {
            retrofitCoroutine.api?.let {
                //开始请求
                val result = it.invoke()
                //请求完成 把数据发送到下游
                emit(result)
            }
        }.flowOn(Dispatchers.IO).onCompletion {
            //处理完成 发生异常也会回掉这里
            callback.onDismissLoading()
            retrofitCoroutine.onHideLoading?.invoke()
        }.onEach {
            //处理上游请求回来的数据 把逻辑放到onEach里面 并且放在catch的上面 是需要让catch同时捕获到上游和下游的异常
            //设置基数数据
            retrofitCoroutine.baseTime = it.timestamp
            retrofitCoroutine.totalRecord = it.totalRecord
            retrofitCoroutine.message = it.message
            //请求完成的回掉
            retrofitCoroutine.onComplete?.invoke()
            //回掉code 给调用方自行处理
            callback.onResponseCode(it.code)
            //处理逻辑
            if (it.code == HttpCode.HTTP_CODE_200) {
                if (it.data != null) {
                    //字典查询
                    if (retrofitCoroutine.onBeforeHandler != null) {
                        val rawData = it.data
                        retrofitCoroutine.onBeforeHandler?.invoke(rawData)
                        retrofitCoroutine.onSuccess?.invoke(rawData)
                    } else {
                        //请求成功
                        retrofitCoroutine.onSuccess?.invoke(it.data)
                    }
                }
                retrofitCoroutine.onSuccessEmptyData?.invoke(it.data)
                retrofitCoroutine.onSuccessEmpty?.invoke()
            } else {
                retrofitCoroutine.onFailed?.invoke(it.message, it.code)
            }
        }.catch { e ->
            //处理异常
            val errorMsg = when (e) {
                is UnknownHostException -> NetworkManage.NETWORK_ERROR_TIPS
                is ConnectException -> NetworkManage.NETWORK_CONNECT_ERROR_TIPS
                is HttpException -> NetworkManage.NETWORK_ERROR_TIPS
                else -> NetworkManage.NETWORK_ERROR_TIPS
            }
            retrofitCoroutine.onFailed?.invoke(errorMsg, 0)
        }.launchIn(viewModel.viewModelScope)

    }
}