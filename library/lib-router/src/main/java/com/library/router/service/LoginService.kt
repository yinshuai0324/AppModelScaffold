package com.library.router.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import com.library.logcat.AppLog

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 16:11
 * 作用描述：登录模块对外提供的服务
 */
interface LoginService : IProvider {
    /**
     * 登录成功
     */
    fun loginSucceed(userInfo: String)

    fun getUserName(): String
}