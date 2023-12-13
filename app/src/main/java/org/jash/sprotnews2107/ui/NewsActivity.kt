package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.ActivityNewsBinding
import org.jash.sprotnews2107.viewmodel.MainViewModel

@Route(path = "/news/main")
class NewsActivity : BaseActivity<ActivityNewsBinding, MainViewModel>() {
    override fun initView() {
        binding.navMain.setOnItemSelectedListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, ARouter.getInstance().build( when(it.itemId) {
                    R.id.nav_video -> "/news/video"
                    R.id.nav_community -> "/news/community"
                    R.id.nav_mine -> "/news/mine"
                    else -> "/news/news"
                }).navigation() as Fragment)
                .commit()
            true
        }
        binding.navMain.selectedItemId = R.id.nav_home
    }

    override fun initData() {
    }

}