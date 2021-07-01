package com.model.home.pages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.PageStatus
import com.model.home.R
import com.model.home.databinding.HomeChildFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 10:36
 * 作用描述：
 */
class HomeChildFragment : BaseFragment<BaseViewModel, HomeChildFragmentBinding>(),
    View.OnClickListener {

    override fun lazyInit() {
        val name = arguments?.getString("name")

        viewBinding.emptyBtn.setOnClickListener(this)
        viewBinding.emptyRetryBtn.setOnClickListener(this)
        viewBinding.loadingBtn.setOnClickListener(this)
        viewBinding.errorBtn.setOnClickListener(this)
        viewBinding.errorRetryBtn.setOnClickListener(this)
        viewBinding.netErrorBtn.setOnClickListener(this)
        viewBinding.netErrorRetryBtn.setOnClickListener(this)
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

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.emptyBtn -> changePageStatus(PageStatus.STATUS_EMPTY)
            R.id.emptyRetryBtn -> changePageStatus(PageStatus.STATUS_EMPTY_RETRY)
            R.id.loadingBtn -> changePageStatus(PageStatus.STATUS_LOADING)
            R.id.errorBtn -> changePageStatus(PageStatus.STATUS_ERROR)
            R.id.errorRetryBtn -> changePageStatus(PageStatus.STATUS_ERROR_RETRY)
            R.id.netErrorBtn -> changePageStatus(PageStatus.STATUS_NET_ERROR)
            R.id.netErrorRetryBtn -> changePageStatus(PageStatus.STATUS_NET_ERROR_RETRY)
        }
        getLifecycleScope().launch(Dispatchers.IO) {
            delay(3000)
            getLifecycleScope().launch(Dispatchers.Main) {
                changePageStatus(PageStatus.STATUS_SUCCEED)
            }
        }
    }

    override fun onRetry() {
        Toast.makeText(requireContext(), "重试监听", Toast.LENGTH_SHORT).show()
    }


}