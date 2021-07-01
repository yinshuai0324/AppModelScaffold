package com.library.widget.status.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.library.widget.R
import com.library.widget.status.MultiState
import com.library.widget.status.MultiStateContainer
import com.library.widget.status.MultiStatePage

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 13:52
 * 作用描述：空状态 带重试按钮
 */
class EmptyRetryState : MultiState() {
    private lateinit var tvEmptyMsg: TextView
    private lateinit var imgEmpty: ImageView
    private lateinit var retryBtn: Button

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.mult_state_empty_retry, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
        tvEmptyMsg = view.findViewById(R.id.tv_empty_msg)
        imgEmpty = view.findViewById(R.id.img_empty)
        retryBtn = view.findViewById(R.id.retryBtn)

        setEmptyMsg(MultiStatePage.config.emptyMsg)
        setEmptyIcon(MultiStatePage.config.emptyIcon)
    }

    override fun enableReload(): Boolean {
        return true
    }

    override fun bindRetryView(): View {
        return retryBtn
    }

    fun setEmptyMsg(emptyMsg: String) {
        tvEmptyMsg.text = emptyMsg
    }

    fun setEmptyIcon(@DrawableRes emptyIcon: Int) {
        imgEmpty.setImageResource(emptyIcon)
    }
}