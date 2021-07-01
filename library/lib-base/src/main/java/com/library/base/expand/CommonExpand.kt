package com.library.base.expand

import android.app.Activity
import com.library.base.activity.BaseActivity
import com.library.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:50
 * 作用描述：公共扩展方法
 */

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}
