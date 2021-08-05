package com.library.router.params

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 10:24
 * 作用描述：跳转参数
 */
class JumpParams : HashMap<String, Any>() {
    /**
     * 添加参数
     */
    fun addParams(key: String, value: Any) {
        this[key] = value
    }


}