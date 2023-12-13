package org.jash.sprotnews2107

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}