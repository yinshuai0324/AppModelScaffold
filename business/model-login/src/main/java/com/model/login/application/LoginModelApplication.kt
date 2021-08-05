package com.model.login.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.alibaba.android.arouter.launcher.ARouter
import com.library.base.application.BaseModelApplication
import com.library.network.NetworkManage
import com.model.login.BuildConfig

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 11:22
 * 作用描述：
 */
class LoginModelApplication : BaseModelApplication() {
    override fun attachBaseContext(context: Context) {
    }

    override fun onCreate(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
        if (BuildConfig.IS_RUN_MODEL) {
            //已模块化运行的话 需要初始化网络请求框架
            NetworkManage.config().build()
        }
    }

    override fun onLowMemory() {
    }

    override fun onTerminate() {
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
    }
}