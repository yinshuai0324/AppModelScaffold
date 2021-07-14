package com.model.splash

import android.animation.Animator
import com.alibaba.android.arouter.launcher.ARouter
import com.library.base.activity.BaseActivity
import com.library.base.viewmodel.BaseViewModel
import com.model.splash.databinding.SplashActivityMainBinding

class SplashMainActivity : BaseActivity<BaseViewModel, SplashActivityMainBinding>(),
    Animator.AnimatorListener {

    override fun initData() {
        viewBinding.animView.addAnimatorListener(this)
        viewBinding.animView.playAnimation()
    }

    override fun createdObserve() {

    }

    override fun onAnimationStart(p0: Animator?) {

    }

    override fun onAnimationEnd(p0: Animator?) {
        ARouter.getInstance().build("/model/HomeMainActivity").navigation()
    }

    override fun onAnimationCancel(p0: Animator?) {

    }

    override fun onAnimationRepeat(p0: Animator?) {

    }
}