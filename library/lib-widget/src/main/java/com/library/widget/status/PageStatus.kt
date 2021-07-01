package com.library.widget.status

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:41
 * 作用描述：
 */
enum class PageStatus {
    /**
     * 页面空状态
     */
    STATUS_EMPTY,

    /**
     * 页面空状态-带重试按钮
     */
    STATUS_EMPTY_RETRY,

    /**
     * 页面加载中状态
     */
    STATUS_LOADING,

    /**
     * 页面成功状态
     */
    STATUS_SUCCEED,

    /**
     * 页面未知错误状态
     */
    STATUS_ERROR,

    /**
     * 页面未知状态带重试按钮
     */
    STATUS_ERROR_RETRY,

    /**
     * 网络错误状态
     */
    STATUS_NET_ERROR,

    /**
     * 网络错误状态-带重试按钮
     */
    STATUS_NET_ERROR_RETRY,
}