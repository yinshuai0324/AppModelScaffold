package com.model.login

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.library.base.activity.BaseActivity
import com.library.base.viewmodel.BaseViewModel
import com.library.router.RouterPath
import com.library.router.service.LoginService
import com.model.login.databinding.LoginActivityLoginBinding
import com.model.login.service.LoginServiceImp

/**
 * 登录页面
 */
@Route(path = RouterPath.PAGE_LOGIN_ACTIVITY, group = RouterPath.GROUP_LOGIN)
class LoginActivity : BaseActivity<BaseViewModel, LoginActivityLoginBinding>() {

    var loginService = LoginServiceImp()

    override fun initData() {
        viewBinding.loginBtn.setOnClickListener {
            loginService.loginSucceed("yinshuai")
            finish()
        }
    }

    override fun createdObserve() {

    }
}