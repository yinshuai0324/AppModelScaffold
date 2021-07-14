package com.app.base.application

import com.library.base.application.BaseApplication
import com.library.network.NetworkManage

/**
 * 创建者：yinshuai
 * 创建时间：2021/6/30 16:21
 * 作用描述：
 */
class MainApplication : BaseApplication() {

    override fun appInit() {
        NetworkManage.config().build()
    }
}