package org.jash.sprotnews2107

import android.app.Application
import androidx.room.Room
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.drawee.backends.pipeline.Fresco
import org.jash.sprotnews2107.dao.NewsDatabase

class App : Application() {
    val database by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, "news")
            .fallbackToDestructiveMigration()
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        Fresco.initialize(this)
    }
}