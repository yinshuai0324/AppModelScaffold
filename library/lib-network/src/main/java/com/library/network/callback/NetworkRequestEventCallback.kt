package com.library.network.callback

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/3 09:38
 * 作用描述：网络请求过程中 事件状态回调
 */
interface NetworkRequestEventCallback {
    /**
     * 隐藏Loading框
     */
    fun onDismissLoading()

    /**
     * 服务器返回的code
     * 自行处理相关操作
     */
    fun onResponseCode(code: Int)

}