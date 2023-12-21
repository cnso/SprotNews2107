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
import org.jash.common.adapter.CommonAdapter
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.logging
import org.jash.common.utils.token
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.adapter.CommentAdapter
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
    val adapter by lazy{ CommentAdapter({
        comment.parentid = it.id
        binding.commentEt.hint = "回复 @${it.user.username}"
    }, {
        repalys.comment = it
        repalys.show(supportFragmentManager, "")
    }) }
    val repalys by lazy { CommentDetailFragment() }
    var flag = false
    override fun initView() {
        binding.collected = collected
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collect.setOnClickListener {
            viewModel.collect(id)
        }
//        binding.comment.setImeActionLabel()
        binding.commentEt.setOnEditorActionListener { _, actionId, _ ->
            if(!flag) {
                flag = true
                viewModel.postComment(comment)
            }
            true
        }
        binding.recycler.adapter = adapter
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        binding.comment = comment
        viewModel.newsLiveData.observe(this, this::loaded)
        viewModel.collectLiveData.observe(this, this::loadCollected)
        viewModel.collectedLiveData.observe(this, this::collected)
        viewModel.postCommentLiveData.observe(this, this::commented)
        viewModel.commentLiveData.observe(this, this::loadComment)
        viewModel.loadNews(id)
        if (token != null) {
            viewModel.loadCollect()
            viewModel.loadComment(id)
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
        this.flag = false
        comment.parentid = 0
        binding.commentEt.hint = "评论"
        binding.commentEt.setText("")
        viewModel.loadComment(id)
    }
    fun loadComment(list:List<Comment>) {
        adapter.clear()
        adapter += list
    }
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { DetailViewModel(database) } }
}