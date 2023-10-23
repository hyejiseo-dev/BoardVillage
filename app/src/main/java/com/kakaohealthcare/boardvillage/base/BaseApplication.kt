package com.kakaohealthcare.boardvillage.base

import android.app.Application
import com.kakaohealthcare.boardvillage.util.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
       // KakaoSdk.init(this, "b1c390ae69c245deed99111f78d2a1dc")
    }
}