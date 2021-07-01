package com.library.base.expand

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:30
 * 作用描述：对Activity类的扩展
 */


/**
 * 使用add+show+hide模式加载fragment
 *
 * 默认显示位置[showPosition]的Fragment，最大Lifecycle为Lifecycle.State.RESUMED
 * 其他隐藏的Fragment，最大Lifecycle为Lifecycle.State.STARTED
 *
 *@param containerViewId 容器id
 *@param showPosition  fragments
 *@param fragmentManager FragmentManager
 *@param fragments  控制显示的Fragments
 */
fun Activity.loadFragmentsTransaction(
    fragmentManager: FragmentManager,
    vararg fragments: Fragment,
    @IdRes containerViewId: Int,
    showPosition: Int,
) {
    if (fragments.isNotEmpty()) {
        fragmentManager.beginTransaction().apply {
            for (index in fragments.indices) {
                val fragment = fragments[index]
                add(containerViewId, fragment, fragment.javaClass.name)
                if (showPosition == index) {
                    setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                } else {
                    hide(fragment)
                    setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                }
            }

        }.commit()
    } else {
        throw IllegalStateException("fragments must not empty")
    }
}


/** 显示需要显示的Fragment[showFragment]，并设置其最大Lifecycle为Lifecycle.State.RESUMED。
 *  同时隐藏其他Fragment,并设置最大Lifecycle为Lifecycle.State.STARTED
 * @param fragmentManager
 * @param showFragment
 */
fun Activity.showHideFragmentTransaction(fragmentManager: FragmentManager, showFragment: Fragment) {
    fragmentManager.beginTransaction().apply {
        show(showFragment)
        setMaxLifecycle(showFragment, Lifecycle.State.RESUMED)
        //获取其中所有的fragment,其他的fragment进行隐藏
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != showFragment) {
                hide(fragment)
                setMaxLifecycle(fragment, Lifecycle.State.STARTED)
            }
        }
    }.commit()
}


