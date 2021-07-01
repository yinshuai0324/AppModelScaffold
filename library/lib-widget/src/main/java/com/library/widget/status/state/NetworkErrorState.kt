package com.library.widget.status.state

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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
 * 作用描述：错误状态
 */
class NetworkErrorState : MultiState() {

    private lateinit var tvErrorMsg: TextView
    private lateinit var imgError: ImageView

    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: MultiStateContainer
    ): View {
        return inflater.inflate(R.layout.mult_network_state_error, container, false)
    }

    override fun onMultiStateViewCreate(view: View) {
        tvErrorMsg = view.findViewById(R.id.tv_error_msg)
        imgError = view.findViewById(R.id.img_error)

        setErrorMsg(MultiStatePage.config.networkErrorMsg)
        setErrorIcon(MultiStatePage.config.networkErrorIcon)
    }


    fun setErrorMsg(errorMsg: String) {
        tvErrorMsg.text = errorMsg
    }

    fun setErrorIcon(@DrawableRes errorIcon: Int) {
        imgError.setImageResource(errorIcon)
    }
}