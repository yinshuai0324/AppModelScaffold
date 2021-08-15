package com.model.home.pages

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.library.base.expand.ToastType
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.widget.status.PageStatus
import com.model.home.R
import com.model.home.databinding.HomeFragmentPage2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment2 : BaseFragment<BaseViewModel, HomeFragmentPage2Binding>(),
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
        viewBinding.infoBtn.setOnClickListener(this)
        viewBinding.errorBtn.setOnClickListener(this)
        viewBinding.succeedBtn.setOnClickListener(this)
        viewBinding.warningBtn.setOnClickListener(this)
        viewBinding.deleteBtn.setOnClickListener(this)
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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.infoBtn -> showToast(ToastType.INFO, "这是InfoToast")
            R.id.succeedBtn -> showToast(ToastType.SUCCESS, "这是SucceedToast")
            R.id.errorBtn -> showToast(ToastType.ERROR, "这是errorToast")
            R.id.warningBtn -> showToast(ToastType.WARNING, "这是warningToast")
            R.id.deleteBtn -> showToast(ToastType.WARNING, "这是deleteToast")
        }
    }
}