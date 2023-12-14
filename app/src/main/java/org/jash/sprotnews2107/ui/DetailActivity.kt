package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.database
import org.jash.sprotnews2107.databinding.ActivityDetailBinding
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.viewmodel.DetailViewModel

@Route(path = "/news/detail")
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    @Autowired
    @JvmField
    var id:Int = 0
    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        viewModel.newsLiveData.observe(this, this::loaded)
        viewModel.loadNews(id)
    }

    fun loaded(news: News) {
        binding.news = news
    }
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { DetailViewModel(database.getNewsDao()) } }
}