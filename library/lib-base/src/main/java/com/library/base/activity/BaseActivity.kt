package com.library.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.library.base.data.EventType
import com.library.base.expand.*
import com.library.base.utils.inflateBindingWithGeneric
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.MultiStateContainer
import com.library.widget.status.MultiStatePage
import com.library.widget.status.PageStatus
import com.library.widget.status.bindMultiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 08:51
 * 作用描述：Activity的基类 MVVM架构 使用ViewBind查找控件 带协程作用域
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope() {

    /**
     * ViewModel
     */
    lateinit var viewModel: VM

    /**
     * ViewBinding
     */
    lateinit var viewBinding: VB

    /**
     * 页面状态显示View
     */
    lateinit var pageStatus: MultiStateContainer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateBindingWithGeneric(layoutInflater)
        setContentView(viewBinding.root)
        //设置状态栏样式
        setBarStyle(statusBarStyle())
        pageStatus = bindMultiState {
            //重试
            onRetry()
        }
        if (defaultLoadingStatus()) {
            pageStatus.changePageStatus(PageStatus.STATUS_LOADING)
        }
        viewModel = createViewModel()
        //初始化路由
        ARouter.getInstance().inject(this)
        handlerViewModelNotice()
        createdObserve()
        initData()
    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 创建订阅
     */
    abstract fun createdObserve()

    /**
     * 页面重试回调
     */
    open fun onRetry() {

    }

    /**
     * 默认是否是加载状态
     */
    open fun defaultLoadingStatus(): Boolean {
        return false
    }

    /**
     * 改变页面状态
     */
    fun changePageStatus(status: PageStatus) {
        pageStatus.changePageStatus(status)
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(getVmClazz(this))
    }

    /**
     * 处理ViewModel的通知
     */
    private fun handlerViewModelNotice() {
        viewModel.eventNoticeData.observe(this) {
            it?.let {
                when (it.type) {
                    EventType.EVENT_TOAST -> {
                        //显示Toast
                        toast(it.toastType, it.desc)
                    }
                    EventType.EVENT_DIALOG -> {
                        //TODO 显示弹窗
                    }
                    EventType.EVENT_SHOW_LOADING_DIALOG -> {
                        //TODO 显示加载框
                    }
                    EventType.EVENT_DISMISS_LOADING_DIALOG -> {
                        //TODO 关闭加载框
                    }
                    EventType.EVENT_CHANGE_PAGE_STATUS -> {
                        //改变页面状态
                        changePageStatus(it.pageStatus)
                    }
                    EventType.EVENT_FINISH_PAGE -> {
                        //关闭当前页面
                        finish()
                    }
                    else -> {
                        //无需处理
                    }
                }
            }
        }
    }

    /**
     * 是否默认处理状态栏和底部导航栏
     */
    open fun statusBarStyle(): StatusBarStyle = StatusBarStyle.DEFAULT


    /**
     * 显示Toast
     */
    fun showToast(type: ToastType = ToastType.INFO, msg: String?) {
        toast(type, msg)
    }
}