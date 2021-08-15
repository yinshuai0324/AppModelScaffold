package com.model.home.pages

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.PageStatus
import com.model.home.databinding.HomeFragmentPage3Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment3 : BaseFragment<BaseViewModel, HomeFragmentPage3Binding>() {


    override fun lazyInit() {
        //模拟页面第一次加载
        lifecycleScope.launch(Dispatchers.IO) {
            delay(500)
            lifecycleScope.launch(Dispatchers.Main) {
                changePageStatus(PageStatus.STATUS_SUCCEED)
            }
        }
    }

    override fun initData() {

    }

    override fun createdObserve() {

    }

    override fun defaultLoadingStatus(): Boolean = true

    companion object {
        fun newInstance(): HomeFragment3 {
            val fragment = HomeFragment3()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}