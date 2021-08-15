package com.model.home.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.PageStatus
import com.model.home.R
import com.model.home.databinding.HomeFragmentPage1Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment1 : BaseFragment<BaseViewModel, HomeFragmentPage1Binding>(),
    View.OnClickListener {


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
        viewBinding.emptyBtn.setOnClickListener(this)
        viewBinding.emptyRetryBtn.setOnClickListener(this)
        viewBinding.errorBtn.setOnClickListener(this)
        viewBinding.errorRetryBtn.setOnClickListener(this)
        viewBinding.networkBtn.setOnClickListener(this)
        viewBinding.networkRetryBtn.setOnClickListener(this)
        viewBinding.loadingBtn.setOnClickListener(this)
    }

    override fun createdObserve() {

    }

    override fun defaultLoadingStatus(): Boolean = true

    companion object {
        fun newInstance(): HomeFragment1 {
            val fragment = HomeFragment1()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.emptyBtn -> changePageStatus(PageStatus.STATUS_EMPTY)
            R.id.emptyRetryBtn -> changePageStatus(PageStatus.STATUS_EMPTY_RETRY)
            R.id.errorBtn -> changePageStatus(PageStatus.STATUS_ERROR)
            R.id.errorRetryBtn -> changePageStatus(PageStatus.STATUS_ERROR_RETRY)
            R.id.networkBtn -> changePageStatus(PageStatus.STATUS_NET_ERROR)
            R.id.networkRetryBtn -> changePageStatus(PageStatus.STATUS_NET_ERROR_RETRY)
            R.id.loadingBtn -> changePageStatus(PageStatus.STATUS_LOADING)
        }
        showSucceedStatus()
    }

    override fun onRetry() {
        Toast.makeText(requireContext(), "重试监听", Toast.LENGTH_SHORT).show()
    }

    private fun showSucceedStatus() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            lifecycleScope.launch(Dispatchers.Main) {
                changePageStatus(PageStatus.STATUS_SUCCEED)
            }
        }
    }
}