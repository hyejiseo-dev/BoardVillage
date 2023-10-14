package com.kakaohealthcare.boardvillage.base

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "b1c390ae69c245deed99111f78d2a1dc")
    }
}