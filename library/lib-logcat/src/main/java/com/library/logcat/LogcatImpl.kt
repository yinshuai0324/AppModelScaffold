package com.library.logcat

import android.util.Log

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/9 17:54
 * 作用描述：Logcat各个功能实现类
 */
class LogcatImpl : LogcatService {


    fun log(level: LogcatLevel, tag: String? = AppLog.logTag, msg: String?) {
        when (level) {
            LogcatLevel.INFO -> logI(tag = tag, msg = msg)
            LogcatLevel.DEBUG -> logD(tag = tag, msg = msg)
            LogcatLevel.ERROR -> logE(tag = tag, msg = msg)
            LogcatLevel.JSON -> logJson(tag = tag, msg = msg)
            LogcatLevel.OBJECT -> logObj(tag = tag, msg = msg)
            LogcatLevel.VERBOSE -> logV(tag = tag, msg = msg)
            LogcatLevel.DATA -> logData(tag = tag, msg = msg)
        }
    }

    override fun logI(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.i(tag, msg)
        }
    }

    override fun logD(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.d(tag, msg)
        }
    }

    override fun logE(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.e(tag, msg)
        }
    }

    override fun logV(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.v(tag, msg)
        }
    }

    override fun logW(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.w(tag, msg)
        }
    }

    override fun logJson(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.i(tag, msg)
        }
    }

    override fun logObj(tag: String?, msg: String?) {
        //TODO 待实现
    }

    override fun logData(tag: String?, msg: String?) {
        if (AppLog.isOpenLogcat) {
            Log.i(tag, msg)
        }
    }
}