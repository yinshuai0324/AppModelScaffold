package com.model.home

import com.alibaba.android.arouter.facade.annotation.Route
import com.library.base.activity.BaseActivity
import com.library.widget.status.PageStatus
import com.model.home.adapter.HomeViewPagerAdapter
import com.model.home.databinding.HomeActivityMainBinding

@Route(path = "/model/HomeMainActivity")
class HomeMainActivity : BaseActivity<HomeMainActivityViewModel, HomeActivityMainBinding>() {

    private lateinit var adapter: HomeViewPagerAdapter

    override fun initData() {
        adapter = HomeViewPagerAdapter(this)
        viewBinding.viewPager.adapter = adapter

        //获取网络数据
        viewModel.getPageData()
    }

    override fun createdObserve() {
        viewModel.pageData.observe(this) {
            adapter.data = it
            adapter.notifyDataSetChanged()
            changePageStatus(PageStatus.STATUS_SUCCEED)
        }
    }

    override fun defaultLoadingStatus() = true

}