package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.adapter.CommonAdapter
import org.jash.common.mvvm.BaseActivity
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.database
import org.jash.sprotnews2107.databinding.ActivitySearchBinding
import org.jash.sprotnews2107.databinding.ActivitySplashBinding
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.viewmodel.SearchViewModel
@Route(path ="/news/search")
class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {
    val adapter by lazy { CommonAdapter<News>(R.layout.news_item, BR.news) }
    @Autowired
    @JvmField
    var key:String = ""
    override fun initView() {
        ARouter.getInstance().inject(this)
        binding.searchBar.text = key
        binding.recycler.adapter = adapter
        binding.search.editText.setOnEditorActionListener { v, _, _ ->
            viewModel.searchNews(v.text.toString())
            key = v.text.toString()
            true
        }
    }

    override fun initData() {
        viewModel.newsListLiveData.observe(this, this::searched)
        viewModel.searchNews(key)
    }
    fun searched(list:List<News>) {
        list.forEach { it.key = key }
        adapter.clear()
        adapter += list
    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { SearchViewModel(database.getNewsDao()) } }

}