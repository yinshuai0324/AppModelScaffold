package com.model.home.pages

import android.os.Bundle
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.model.home.databinding.HomeChildFragmentBinding

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 10:36
 * 作用描述：
 */
class HomeChildFragment : BaseFragment<BaseViewModel, HomeChildFragmentBinding>() {

    override fun lazyInit() {
        val name = arguments?.getString("name")
        viewBinding.content.text = name
    }

    override fun initData() {

    }

    override fun createdObserve() {

    }

    companion object {
        fun newInstance(name: String): HomeChildFragment {
            val fragment = HomeChildFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            fragment.arguments = bundle
            return fragment
        }
    }
}