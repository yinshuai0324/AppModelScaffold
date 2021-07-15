package com.model.home.pages

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.PageStatus
import com.model.home.databinding.HomeFragmentPage2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment2 : BaseFragment<BaseViewModel, HomeFragmentPage2Binding>() {


    override fun lazyInit() {
        //模拟页面第一次加载
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
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
        fun newInstance(): HomeFragment2 {
            val fragment = HomeFragment2()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}