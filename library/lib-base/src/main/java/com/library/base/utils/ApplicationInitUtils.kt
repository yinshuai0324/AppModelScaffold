package com.library.base.utils

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.library.base.application.BaseModelApplication
import com.library.base.application.ModelApplicationManage

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 14:07
 * 作用描述：模块application初始化的工具类
 */
object ApplicationInitUtils {
    private val appInitList = arrayListOf<BaseModelApplication>()

    /**
     * 初始化AttachBaseContext
     */
    fun initAttachBaseContext(context: Context) {
        ModelApplicationManage.applications.forEach {
            try {
                val clazz = Class.forName(it)
                val modelApplication = clazz.newInstance()
                appInitList.add(modelApplication as BaseModelApplication)
                //调用attachBaseContext方法
                modelApplication.attachBaseContext(context)
            } catch (e: Exception) {
                Log.e("===>>>", "初始化模块Application出错：${e.message}")
            }
        }
    }

    /**
     * 初始化Create方法
     */
    fun initOnCreate(application: Application) {
        appInitList.forEach {
            it.onCreate(application)
        }
    }


    /**
     * 初始化内存不足的回调
     */
    fun initOnLowMemory() {
        appInitList.forEach {
            it.onLowMemory()
        }
    }


    /**
     * 初始化app终止回调
     */
    fun initOnTerminate() {
        appInitList.forEach {
            it.onTerminate()
        }
    }

    /**
     * 初始化配置改变的回调
     */
    fun initOnConfigurationChanged(newConfig: Configuration) {
        appInitList.forEach {
            it.onConfigurationChanged(newConfig)
        }
    }
}