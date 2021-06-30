package com.library.base.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 13:57
 * 作用描述：模块初始化的基类
 */
abstract class BaseModelApplication {


    /**
     * 在onCreate之前执行
     */
    abstract fun attachBaseContext(context: Context)

    /**
     * 创建
     */
    abstract fun onCreate(application: Application)

    /**
     * 内存不够时调用
     */
    abstract fun onLowMemory()

    /**
     * APP终止时调用
     */
    abstract fun onTerminate()

    /**
     * 配置发生改变时调用
     */
    abstract fun onConfigurationChanged(newConfig: Configuration)
}