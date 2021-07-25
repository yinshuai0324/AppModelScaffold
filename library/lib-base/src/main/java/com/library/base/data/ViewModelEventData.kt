package com.library.base.data

import com.library.base.expand.ToastType
import com.library.widget.status.PageStatus

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 16:52
 * 作用描述：
 */
data class ViewModelEventData(
    val type: EventType = EventType.EVENT_NONE,
    val pageStatus: PageStatus = PageStatus.STATUS_SUCCEED,
    val title: String = "",
    val desc: String = "",
    val toastType: ToastType = ToastType.INFO
)


enum class EventType {
    /**
     * 事件类型：显示Toast
     */
    EVENT_TOAST,

    /**
     * 事件类型：显示弹窗
     */
    EVENT_DIALOG,

    /**
     * 事件类型：显示加载弹窗
     */
    EVENT_SHOW_LOADING_DIALOG,

    /**
     * 事件类型：关闭弹窗
     */
    EVENT_DISMISS_LOADING_DIALOG,

    /**
     * 事件类型：关闭当前页面
     */
    EVENT_FINISH_PAGE,

    /**
     * 事件类型：改变页面状态
     */
    EVENT_CHANGE_PAGE_STATUS,

    /**
     * 事件类型：无 不处理该事件类型
     */
    EVENT_NONE
}
