package com.model.login.service

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.library.logcat.AppLog
import com.library.router.RouterPath
import com.library.router.service.LoginService

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 16:27
 * 作用描述：登录模块对外提供的服务
 */
@Route(path = RouterPath.SERVICE_LOGIN, name = "登录模块服务")
class LoginServiceImp : LoginService {
    var loginStatusChange = MutableLiveData<String>()

    override fun loginStatusChange(): MutableLiveData<String> {
        return loginStatusChange
    }


    override fun init(context: Context?) {
        AppLog.log("LoginServiceImp init")
    }
}