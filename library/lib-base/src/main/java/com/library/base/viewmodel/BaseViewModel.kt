package com.library.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.base.data.EventType
import com.library.base.data.ViewModelEventData
import com.library.base.livedata.SingleLiveEvent
import com.library.network.ApiRequest
import com.library.network.NetworkManage
import com.library.network.callback.NetworkRequestEventCallback
import com.library.network.data.NetworkData
import com.library.network.dsl.NetworkRequestDsl
import com.library.widget.status.PageStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:46
 * 作用描述：ViewModel的基类
 */
open class BaseViewModel : ViewModel() {
    /**
     * 事件通知
     */
    val eventNoticeData = SingleLiveEvent<ViewModelEventData>()


    /**
     * 获取Api服务
     */
    fun <T> getApiService(key: String = NetworkManage.GLOBAL_API_KEY, clazz: Class<T>): T {
        return NetworkManage.createApiService(key, clazz)
    }

    /**
     * 获取Api服务
     */
    fun <T> getApiService(clazz: Class<T>): T {
        return NetworkManage.createApiService(NetworkManage.GLOBAL_API_KEY, clazz)
    }


    /**
     * 显示Toast
     */
    fun toast(msg: String?) {
        msg?.let {
            eventNoticeData.value = ViewModelEventData(type = EventType.EVENT_TOAST, desc = it)
        }
    }

    /**
     * 显示弹窗
     */
    fun showDialog(title: String = "", msg: String = "") {
        eventNoticeData.value =
            ViewModelEventData(EventType.EVENT_DIALOG, title = title, desc = msg)
    }

    /**
     * 显示加载框
     */
    fun showLoading(msg: String = "正在加载中...") {
        eventNoticeData.value = ViewModelEventData(EventType.EVENT_SHOW_LOADING_DIALOG, desc = msg)
    }

    /**
     * 关闭加载框
     */
    fun dismissLoading() {
        eventNoticeData.value = ViewModelEventData(EventType.EVENT_DISMISS_LOADING_DIALOG)
    }

    /**
     * 关闭当前页面
     */
    fun finishPage() {
        eventNoticeData.value = ViewModelEventData(EventType.EVENT_FINISH_PAGE)
    }

    /**
     * 改变页面状态
     */
    fun changePageStatus(status: PageStatus) {
        eventNoticeData.value =
            ViewModelEventData(EventType.EVENT_CHANGE_PAGE_STATUS, pageStatus = status)
    }


    /**
     * 网络请求 需要自己捕获异常
     */
    fun <T> apiRequest(block: suspend () -> NetworkData<T>?) {
        viewModelScope.launch(Dispatchers.Main) {
            ApiRequest.apiRequest(block)
        }
    }


    /**
     * 安全调用 网络同步请求请求
     */
    suspend fun <T> safeApiRequestAwait(api: suspend () -> NetworkData<T>): NetworkData<T> {
        return ApiRequest.safeApiRequestAwait(object : NetworkRequestEventCallback {
            override fun dismissLoading() {
                dismissLoading()
            }
        }, api)
    }

    /**
     * 安全调用-异步请求
     */
    fun <T> safeApiRequest(dsl: NetworkRequestDsl<T>.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            ApiRequest.safeApiRequest<T>(this@BaseViewModel, object : NetworkRequestEventCallback {
                override fun dismissLoading() {
                    dismissLoading()
                }
            }, dsl)
        }
    }

}