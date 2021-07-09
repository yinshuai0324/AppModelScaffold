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
    fun dismissLoading()

}