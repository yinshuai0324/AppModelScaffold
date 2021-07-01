package com.library.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.library.base.expand.getVmClazz
import com.library.base.utils.inflateBindingWithGeneric
import com.library.base.viewmodel.BaseViewModel

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:02
 * 作用描述：Fragment 基类
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateBindingWithGeneric(layoutInflater, container, false)
        viewModel = createViewModel()
        createdObserve()
        return viewBinding.root
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
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(getVmClazz(this))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isLoadData = false
    }
}