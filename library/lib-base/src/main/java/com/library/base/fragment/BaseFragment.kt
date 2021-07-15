package com.library.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.library.base.data.EventType
import com.library.base.expand.getVmClazz
import com.library.base.expand.toast
import com.library.base.utils.inflateBindingWithGeneric
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.MultiStateContainer
import com.library.widget.status.MultiStatePage
import com.library.widget.status.PageStatus

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:02
 * 作用描述：Fragment 基类  MVVM架构 使用ViewBind查找控件
 * Androidx 下的Fragment 新增了setMaxLifecycle 所以一般情况下以及使用ViewPager2 能够正确的回调 onResume
 * 对于使用add+show+hide的方式 请使用ActivityExpand.kt 里面的loadFragmentsTransaction 和 showHideFragmentTransaction
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {
    /**
     * 是否已加载数据
     */
    private var isLoadData = false

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
    private lateinit var pageStatus: MultiStateContainer


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateBindingWithGeneric(layoutInflater, container, false)
        pageStatus = MultiStatePage.bindMultiState(viewBinding.root) {
            //重试处理
            onRetry()
        }
        if (defaultLoadingStatus()) {
            pageStatus.changePageStatus(PageStatus.STATUS_LOADING)
        }
        viewModel = createViewModel()
        handlerViewModelNotice()
        createdObserve()
        return pageStatus
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    override fun onResume() {
        super.onResume()
        if (!isLoadData && !isHidden) {
            lazyInit()
            isLoadData = true
        } else {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisible()
    }

    /**
     * 懒加载数据
     */
    abstract fun lazyInit()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 创建订阅
     */
    abstract fun createdObserve()

    /**
     * 界面可见时回调
     */
    open fun onVisible() {

    }

    /**
     * 界面不可见时回调
     */
    open fun onInvisible() {

    }

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
     * 获取协程作用范围
     */
    fun getLifecycleScope() = viewLifecycleOwner.lifecycleScope

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
                        if (isAdded) {
                            requireContext().toast(it.desc)
                        }
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
                    else -> {
                        //无需处理
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isLoadData = false
    }
}