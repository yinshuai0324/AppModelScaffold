package com.library.widget.status

import android.app.Activity
import android.view.View

/**
 * @ProjectName: MultiStatePage
 * @CreateDate: 2020/9/17 17:10
 */
fun View.bindMultiState(onRetryEventListener: OnRetryEventListener = OnRetryEventListener {  }) =
    MultiStatePage.bindMultiState(this, onRetryEventListener)

fun Activity.bindMultiState(onRetryEventListener: OnRetryEventListener = OnRetryEventListener {  }) =
    MultiStatePage.bindMultiState(this, onRetryEventListener)