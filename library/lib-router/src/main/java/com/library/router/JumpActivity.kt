package com.library.router

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.library.router.params.JumpParams

/**
 * 创建者：yinshuai
 * 创建时间：2021/8/5 10:20
 * 作用描述：跳转管理
 */
class JumpActivity {
    companion object {

        /**
         * 普通跳转
         */
        fun jump(group: String, path: String) {
            jumpHandler(group = group, path = path)
        }

        /**
         * 普通跳转-带参数
         */
        fun jump(group: String, path: String, params: JumpParams?) {
            jumpHandler(group = group, path = path, params = params)
        }

        /**
         * 跳转带是否关闭前一个Activity
         */
        fun jump(activity: Activity?, group: String, path: String, isKill: Boolean) {
            jumpHandler(activity = activity, group = group, path = path, isKill = isKill)
        }

        /**
         * 跳转带返回值
         */
        fun jump(activity: Activity?, group: String, path: String, requestCode: Int) {
            jumpHandler(activity = activity, group = group, path = path, requestCode = requestCode)
        }


        /**
         * 跳转到指定的页面
         */
        private fun jumpHandler(
            activity: Activity? = null,
            group: String,
            path: String,
            uri: Uri? = null,
            params: JumpParams? = null,
            objParams: Any? = null,
            requestCode: Int = 0,
            inAnim: Int = R.anim.anim_page_in,
            outAnim: Int = R.anim.anim_page_out,
            isKill: Boolean = false
        ) {
            val router = ARouter.getInstance()
            //添加目标页面信息
            val build = if (uri != null) {
                router.build(uri)
            } else {
                router.build(path, group)
            }
            //添加参数
            params?.let {
                val bundle = Bundle()
                bundle.putSerializable("params", it)
                build.with(bundle)
            }
            //添加对象参数
            objParams?.let {
                build.withObject("objParams", it)
            }
            //添加转场动画
            build.withTransition(inAnim, outAnim)
            //添加requestCode
            if (requestCode != 0 && activity != null) {
                build.navigation(activity, requestCode)
            } else {
                build.navigation()
            }
            //是否销毁之前的Activity
            if (isKill) {
                activity?.finish()
            }
        }
    }
}