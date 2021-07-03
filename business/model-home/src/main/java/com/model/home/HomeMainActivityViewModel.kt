package com.model.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.library.base.viewmodel.BaseViewModel
import com.model.home.api.HomeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 10:54
 * 作用描述：
 */
class HomeMainActivityViewModel : BaseViewModel() {
    val pageData = MutableLiveData<ArrayList<String>>()
    val apiService = getApiService(HomeApiService::class.java)

    fun getPageData() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            viewModelScope.launch(Dispatchers.Main) {
                pageData.value = arrayListOf("第一个页面", "第二个页面", "第三个页面", "第四个页面")
            }
        }

        safeApiRequest<String> {
            api = { apiService.getData() }
            onSuccess {

            }
            onFailed { errorMsg, code ->

            }
        }
    }
}