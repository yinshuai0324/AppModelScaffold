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
        retrofitCoroutine.api.let {
            //开始请求
            val work = viewModel.viewModelScope.async(Dispatchers.IO) {
                try {
                    it?.invoke()
                } catch (e: UnknownHostException) {
                    viewModel.viewModelScope.launch(Dispatchers.Main) {
                        val errorMsg = NetworkManage.NETWORK_ERROR_TIPS
                        retrofitCoroutine.onFailed?.invoke(errorMsg, 0)
                    }
                    Log.e("===>>>", e.toString())
                    null
                } catch (e: ConnectException) {
                    viewModel.viewModelScope.launch(Dispatchers.Main) {
                        val errorMsg = NetworkManage.NETWORK_CONNECT_ERROR_TIPS
                        retrofitCoroutine.onFailed?.invoke(errorMsg, 0)
                    }
                    Log.e("===>>>", e.toString())
                    null
                } catch (e: Exception) {
                    viewModel.viewModelScope.launch(Dispatchers.Main) {
                        val errorMsg = NetworkManage.NETWORK_ERROR_TIPS
                        retrofitCoroutine.onFailed?.invoke(errorMsg, 0)
                    }
                    Log.e("===>>>", e.toString())
                    null
                } finally {
                    viewModel.viewModelScope.launch(Dispatchers.Main) {
                        callback.onDismissLoading()
                        retrofitCoroutine.onHideLoading?.invoke()
                    }
                }
            }
            //关闭时取消任务
            work?.invokeOnCompletion {
                if (work.isCancelled) {
                    work.cancel()
                    retrofitCoroutine.clean()
                }
            }
            try {
                //获取数据
                val response = work?.await()
                if (response != null) {
                    //设置基数数据
                    retrofitCoroutine.baseTime = response.timestamp
                    retrofitCoroutine.totalRecord = response.totalRecord
                    retrofitCoroutine.message = response.message

                    //请求完成的回掉
                    retrofitCoroutine.onComplete?.invoke()
                    response.let {
                        if (response.code == 401) {
                            //登录失效
                        }
//                        if (response.code == -1002 || response.code == 500 || response.code == 600) {
//                            //服务异常
//                            if (retrofitCoroutine.openLoadStatus) {
//                                netWorkRequestFailure()
//                            }
//                            if (retrofitCoroutine.startNumber == 1) {
//                                refreshFailure()
//                            } else {
//                                loadMoreFailure()
//                            }
//                        }

//                        if (response.code == -4001) {
//                            //签名不合法
//                            if (retrofitCoroutine.openLoadStatus) {
//                                netWorkRequestFailure()
//                            }
//                            if (retrofitCoroutine.startNumber == 1) {
//                                refreshFailure()
//                            } else {
//                                loadMoreFailure()
//                            }
//                        }

                        if (response.code == HttpCode.HTTP_CODE_200) {
                            if (response.data != null) {
                                //字典查询
                                if (retrofitCoroutine.onBeforeHandler != null) {
                                    val rawData = response.data
                                    retrofitCoroutine.onBeforeHandler?.invoke(rawData)
                                    retrofitCoroutine.onSuccess?.invoke(rawData)
                                } else {
                                    //请求成功
                                    retrofitCoroutine.onSuccess?.invoke(response.data)
                                }
                            }
                            retrofitCoroutine.onSuccessEmptyData?.invoke(response.data)
                            retrofitCoroutine.onSuccessEmpty?.invoke()
                        } else {
                            retrofitCoroutine.onFailed?.invoke(
                                response.message,
                                response.code
                            )
                        }
                    }
                } else {
                    //等于null 肯定是抛异常了 不用处理
                }
            } catch (e: HttpException) {
                retrofitCoroutine.onFailed?.invoke(
                    e.message,
                    -1
                )
            } catch (e: Exception) {
                retrofitCoroutine.onFailed?.invoke(
                    e.message,
                    -1
                )
            }

        }
    }
}