package com.model.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.model.home.pages.HomeChildFragment

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
        return HomeChildFragment.newInstance(data[position])
    }
}