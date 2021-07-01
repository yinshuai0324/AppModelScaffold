package com.library.base.viewmodel

import androidx.lifecycle.ViewModel
import com.library.base.data.EventType
import com.library.base.data.ViewModelEventData
import com.library.base.livedata.SingleLiveEvent
import com.library.widget.status.PageStatus

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
}