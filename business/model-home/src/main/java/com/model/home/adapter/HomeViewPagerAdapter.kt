package com.model.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.model.home.pages.HomeFragment1
import com.model.home.pages.HomeFragment2
import com.model.home.pages.HomeFragment3
import com.model.home.pages.HomeFragment4

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 10:45
 * 作用描述：
 */
class HomeViewPagerAdapter constructor(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    var data = arrayListOf<String>()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment1.newInstance()
            1 -> HomeFragment2.newInstance()
            2 -> HomeFragment3.newInstance()
            3 -> HomeFragment4.newInstance()
            else -> HomeFragment1.newInstance()
        }
    }
}