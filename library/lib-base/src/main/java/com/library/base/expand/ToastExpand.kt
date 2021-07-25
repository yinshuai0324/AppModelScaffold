package com.library.base.expand

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.core.content.res.ResourcesCompat
import com.library.base.R
import www.sanju.motiontoast.MotionToast

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
        MotionToast.darkToast(this,"提示",it,MotionToast.TOAST_INFO,MotionToast.GRAVITY_BOTTOM,MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(this, R.font.helvetica_regular))
    }
}

fun Activity.toastError(msg: String?) {
    msg?.let {
        MotionToast.darkToast(this,"错误",it,MotionToast.TOAST_ERROR,MotionToast.GRAVITY_BOTTOM,MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(this, R.font.helvetica_regular))
    }
}

fun Activity.toastSucceed(msg: String?) {
    msg?.let {
        MotionToast.darkToast(this,"成功",it,MotionToast.TOAST_SUCCESS,MotionToast.GRAVITY_BOTTOM,MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(this, R.font.helvetica_regular))
    }
}

fun Activity.toastWarning(msg: String?) {
    msg?.let {
        MotionToast.darkToast(this,"警告",it,MotionToast.TOAST_WARNING,MotionToast.GRAVITY_BOTTOM,MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(this, R.font.helvetica_regular))
    }
}

fun Activity.toastDelete(msg: String?) {
    msg?.let {
        MotionToast.darkToast(this,"删除",it,MotionToast.TOAST_DELETE,MotionToast.GRAVITY_BOTTOM,MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont
                (this, R.font.helvetica_regular))
    }
}

enum class ToastType{
    INFO,
    ERROR,
    SUCCESS,
    WARNING,
    DELETE
}




