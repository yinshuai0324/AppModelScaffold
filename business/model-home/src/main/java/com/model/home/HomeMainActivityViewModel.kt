package com.model.home

import androidx.lifecycle.MutableLiveData
import com.library.base.viewmodel.BaseViewModel

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 10:54
 * 作用描述：
 */
class HomeMainActivityViewModel : BaseViewModel() {
    val pageData = MutableLiveData<ArrayList<String>>()

    fun getPageData() {
        pageData.value = arrayListOf("第一个页面", "第二个页面", "第三个页面", "第四个页面")
    }
}