package com.library.network.data

import com.library.network.code.HttpCode

/**
 * 网络数据包装实体类
 */
class NetworkData<T> {
    var code: Int = 0

    var message: String? = ""
    val pageNum: Int = 0
    val pageSize: Int = 0
    val time: Long = 0
    val totalPage: Int = 0
    val totalRecord: Int = 0
    val data: T? = null
    val timestamp: Long = 0

    constructor()
    constructor(code: Int, message: String)

    fun isSucceed(): Boolean {
        return code == HttpCode.HTTP_CODE_200
    }
}