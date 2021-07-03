package com.library.network.dsl

import com.library.network.data.NetworkData


class NetworkRequestDsl<T> {

    var api: (suspend () -> NetworkData<T>)? = null
    var startNumber: Int = 0
    var openLoadStatus = false

    internal var onLoading: (() -> Unit)? = null
        private set
    internal var onQueryDict: (suspend (T) -> Unit)? = null
        private set
    internal var onSuccess: (suspend (T) -> Unit)? = null
        private set
    internal var onSuccessEmpty: (() -> Unit)? = null
        private set
    internal var onSuccessEmptyData: (suspend (T?) -> Unit)? = null
        private set
    internal var onComplete: (() -> Unit)? = null
        private set
    internal var onFailed: ((error: String?, code: Int?) -> Unit)? = null
        private set
    internal var onHideLoading: (() -> Unit)? = null
        private set


    /**
     * 基础数据
     */
    var totalRecord: Int = 0
    var baseTime: Long = 0
    var message: String? = ""


    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFailed = null
        onLoading = null
        onSuccessEmpty = null
        onHideLoading = null
    }

    fun onLoading(block: () -> Unit) {
        this.onLoading = block
    }

    fun onHideLoading(block: () -> Unit) {
        this.onHideLoading = block
    }

    fun onQueryDict(block: suspend (T) -> Unit) {
        this.onQueryDict = block
    }

    fun onSuccess(block: suspend (T) -> Unit) {
        this.onSuccess = block
    }

    fun onSuccessEmpty(block: () -> Unit) {
        this.onSuccessEmpty = block
    }

    fun onSuccessEmptyData(block: suspend (T?) -> Unit) {
        this.onSuccessEmptyData = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    fun onFailed(block: (errorMsg: String?, code: Int?) -> Unit) {
        this.onFailed = block
    }

}