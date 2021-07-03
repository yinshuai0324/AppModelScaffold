package com.model.home.api

import com.library.network.data.NetworkData

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/2 15:40
 * 作用描述：首页模块Api服务
 */
interface HomeApiService {
    suspend fun getData(): NetworkData<String>
}