package com.library.base.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.library.base.utils.ApplicationInitUtils

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 14:01
 * 作用描述：Application的基类
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInit()
        ApplicationInitUtils.initOnCreate(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        ApplicationInitUtils.initAttachBaseContext(base)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationInitUtils.initOnLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationInitUtils.initOnTerminate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ApplicationInitUtils.initOnConfigurationChanged(newConfig)
    }

    /**
     * 初始化
     */
    abstract fun appInit()
}