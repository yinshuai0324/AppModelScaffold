package com.library.base.expand

import android.app.Activity
import android.content.Context
import com.app.toast.ToastX

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 19:10
 * 作用描述：Toast扩展类
 */


fun Context.toast( type:ToastType,msg: String?){
    if (this is Activity){
        msg?.let {
            when(type){
                ToastType.INFO->this.toast(it)
                ToastType.SUCCESS->this.toastSucceed(it)
                ToastType.ERROR->this.toastError(it)
                ToastType.WARNING->this.toastWarning(it)
                ToastType.DELETE->this.toastDelete(it)
            }
        }
    }
}

fun Activity.toast(msg: String?) {
    msg?.let {
        ToastX.showInfoToast(this,msg)
    }
}

fun Activity.toastError(msg: String?) {
    msg?.let {
        ToastX.showErrorToast(this,msg)
    }
}

fun Activity.toastSucceed(msg: String?) {
    msg?.let {
        ToastX.showSucceedToast(this,msg)
    }
}

fun Activity.toastWarning(msg: String?) {
    msg?.let {
        ToastX.showWarnToast(this,msg)
    }
}

fun Activity.toastDelete(msg: String?) {
    msg?.let {
        ToastX.showWarnToast(this,msg)
    }
}

enum class ToastType{
    INFO,
    ERROR,
    SUCCESS,
    WARNING,
    DELETE
}




