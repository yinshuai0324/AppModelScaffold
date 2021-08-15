package com.library.router.service

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 18:35
 * 作用描述：
 */
interface HomeService : IProvider {
    fun loginSucceed(userName: String)
}