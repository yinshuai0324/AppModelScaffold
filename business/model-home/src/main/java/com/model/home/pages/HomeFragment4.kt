package com.model.home.pages

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.library.base.fragment.BaseFragment
import com.library.base.viewmodel.BaseViewModel
import com.library.router.JumpActivity
import com.library.router.RouterPath
import com.library.router.service.LoginService
import com.library.widget.status.PageStatus
import com.model.home.databinding.HomeFragmentPage4Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment4 : BaseFragment<BaseViewModel, HomeFragmentPage4Binding>() {

    @Autowired(name = RouterPath.SERVICE_LOGIN)
    lateinit var loginService: LoginService

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
        viewBinding.loginBtn.setOnClickListener {
            JumpActivity.jump(RouterPath.GROUP_LOGIN, RouterPath.PAGE_LOGIN_ACTIVITY)
        }
    }

    override fun onVisible() {
        if (::loginService.isInitialized && isAdded) {
            if (!TextUtils.isEmpty(loginService.getUserName())) {
                viewBinding.userInfo.text = "当前用户:${loginService.getUserName()}"
            } else {
                viewBinding.userInfo.text = "当前用户:未登录"
            }
        }
    }

    override fun createdObserve() {

    }

    override fun defaultLoadingStatus(): Boolean = true


    companion object {
        fun newInstance(): HomeFragment4 {
            val fragment = HomeFragment4()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}