package com.library.base.expand

import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:50
 * 作用描述：状态栏管理
 */


/**
 * Activity默认处理
 */
fun FragmentActivity.defaultActivityBar() {
    UltimateBarX.with(this).transparent().light(true).fitWindow(true).applyStatusBar()
    UltimateBarX.with(this).transparent().light(true).fitWindow(false).applyNavigationBar()
}


/**
 * 改变状态栏颜色
 * 透明状态栏
 */
fun FragmentActivity.transparentBar() {
    UltimateBarX.with(this).transparent().applyStatusBar()
}

/**
 * 改变状态栏颜色
 * @param color 状态栏颜色
 * @param fontIsDark 状态栏字体是否是深色
 */
fun FragmentActivity.changeStatusBarColor(color: Int, fontIsDark: Boolean) {
    UltimateBarX.with(this).colorRes(color).light(!fontIsDark).applyStatusBar()
}



