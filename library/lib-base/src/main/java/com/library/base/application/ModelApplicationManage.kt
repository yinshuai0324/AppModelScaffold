package com.library.base.application

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 16:46
 * 作用描述：业务模块的Application管理
 */
object ModelApplicationManage {

    val applications = arrayListOf<String>()


    init {
        //开屏页
        applications.add("com.model.splash.application.SplashModelApplication")
        //首页
        applications.add("com.model.home.application.HomeModelApplication")
    }

}