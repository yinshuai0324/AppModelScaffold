package com.model.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.launcher.ARouter

class SplashMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity_main)

        findViewById<Button>(R.id.jumpBtn).setOnClickListener {
            ARouter.getInstance().build("/model/HomeMainActivity").navigation()
        }
    }
}