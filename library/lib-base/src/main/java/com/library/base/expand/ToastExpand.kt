package com.library.base.expand

import android.content.Context
import android.widget.Toast

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 19:10
 * 作用描述：Toast扩展类
 */

fun Context.toast(msg: String?) {
    msg?.let {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}