package com.model.home.service

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.library.logcat.AppLog
import com.library.router.RouterPath
import com.library.router.service.HomeService

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 18:36
 * 作用描述：
 */
@Route(path = RouterPath.SERVICE_HOME, name = "首页模块服务")
class HomeServiceImp : HomeService {

    override fun loginSucceed(userName: String) {
        AppLog.log("登录成功:${userName}")
    }

    override fun init(context: Context?) {
        AppLog.log("HomeServiceImp init")
    }
}