package com.model.home

import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.library.base.activity.BaseActivity
import com.library.router.RouterPath
import com.library.widget.status.PageStatus
import com.model.home.adapter.HomeViewPagerAdapter
import com.model.home.databinding.HomeActivityMainBinding

@Route(path = RouterPath.PAGE_HOME_MAIN_ACTIVITY, group = RouterPath.GROUP_HOME)
class HomeMainActivity : BaseActivity<HomeMainActivityViewModel, HomeActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: HomeViewPagerAdapter

    override fun initData() {
        adapter = HomeViewPagerAdapter(this)
        viewBinding.viewPager.adapter = adapter

        //获取网络数据
        viewModel.getPageData()

        //注册切换回调
        viewBinding.viewPager.registerOnPageChangeCallback(viewPagerCallback)

        //注册点击回调
        viewBinding.bottomBar.setOnNavigationItemSelectedListener(this)
    }

    override fun createdObserve() {
        viewModel.pageData.observe(this) {
            adapter.data = it
            adapter.notifyDataSetChanged()
            changePageStatus(PageStatus.STATUS_SUCCEED)
        }
    }

    private val viewPagerCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> viewBinding.bottomBar.selectedItemId = R.id.menu1
                1 -> viewBinding.bottomBar.selectedItemId = R.id.menu2
                2 -> viewBinding.bottomBar.selectedItemId = R.id.menu3
                3 -> viewBinding.bottomBar.selectedItemId = R.id.menu4
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> viewBinding.viewPager.currentItem = 0
            R.id.menu2 -> viewBinding.viewPager.currentItem = 1
            R.id.menu3 -> viewBinding.viewPager.currentItem = 2
            R.id.menu4 -> viewBinding.viewPager.currentItem = 3
        }
        return true
    }

}