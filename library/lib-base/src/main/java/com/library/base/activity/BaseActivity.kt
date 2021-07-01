package com.library.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.library.base.expand.bindView
import com.library.base.expand.getVmClazz
import com.library.base.utils.inflateBindingWithGeneric
import com.library.base.viewmodel.BaseViewModel

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 08:51
 * 作用描述：
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    /**
     * ViewModel
     */
    lateinit var viewModel: VM

    /**
     * ViewBinding
     */
    lateinit var viewBinding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateBindingWithGeneric(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = createViewModel()
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
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(getVmClazz(this))
    }
}