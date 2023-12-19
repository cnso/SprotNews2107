package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.logging
import org.jash.common.utils.token
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.database
import org.jash.sprotnews2107.databinding.ActivityDetailBinding
import org.jash.sprotnews2107.entity.Comment
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.viewmodel.DetailViewModel

@Route(path = "/news/detail")
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    @Autowired
    @JvmField
    var id:Int = 0
    val collected = ObservableBoolean(false)
    val comment by lazy{Comment("", null, 0, id, 0, null, 0)}
    override fun initView() {
        binding.collected = collected
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collect.setOnClickListener {
            viewModel.collect(id)
        }
//        binding.comment.setImeActionLabel()
        binding.commentEt.setOnEditorActionListener { _, actionId, _ ->
            logging("actionId: $actionId")
            viewModel.postComment(comment)
            true
        }
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        binding.comment = comment
        viewModel.newsLiveData.observe(this, this::loaded)
        viewModel.collectLiveData.observe(this, this::loadCollected)
        viewModel.collectedLiveData.observe(this, this::collected)
        viewModel.postCommentLiveData.observe(this, this::commented)
        viewModel.loadNews(id)
        if (token != null) {
            viewModel.loadCollect()
        }
    }

    fun loadCollected(list:List<News>) {
        collected.set(list.find { it.id == id } != null)
    }
    fun collected(str:String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        binding.news?.let { it.collects += if(collected.get()) 1  else -1 }
        logging("collects: ${binding.news?.collects}")
    }
    fun loaded(news: News) {
        binding.news = news
    }
    fun commented(flag: Boolean) {
        binding.commentEt.setText("")
    }
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { DetailViewModel(database) } }
}