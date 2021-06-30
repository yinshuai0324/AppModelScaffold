package com.model.home.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.library.base.application.BaseModelApplication

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 16:48
 * 作用描述：首页Application
 */
class HomeModelApplication : BaseModelApplication() {

    override fun attachBaseContext(context: Context) {
        Log.i("===>>>", "HomeModelApplication -> attachBaseContext")
    }

    override fun onCreate(application: Application) {
        Log.i("===>>>", "HomeModelApplication -> onCreate")
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(application)
    }

    override fun onLowMemory() {
        Log.i("===>>>", "HomeModelApplication -> onLowMemory")
    }

    override fun onTerminate() {
        Log.i("===>>>", "HomeModelApplication -> onTerminate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.i("===>>>", "HomeModelApplication -> onConfigurationChanged")
    }
}