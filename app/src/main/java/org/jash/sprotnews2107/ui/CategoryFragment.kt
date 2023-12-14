package org.jash.sprotnews2107.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.adapter.CommonAdapter
import org.jash.common.mvvm.BaseFragment
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.database
import org.jash.sprotnews2107.databinding.FragmentCategoryBinding
import org.jash.sprotnews2107.entity.News
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.viewmodel.CategoryViewModel


@Route(path = "/news/category")
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    @Autowired
    @JvmField
    var id:Int = 0
    val adapter by lazy { CommonAdapter<News>(R.layout.news_item, BR.news) {
        ARouter.getInstance().build("/news/detail")
            .withInt("id", it.id)
            .navigation()
    } }
    var page = 1
    val size = 10
    override fun initView() {
        binding.recycler.adapter = adapter
        binding.refresh.setOnRefreshListener {
            page = 1
            viewModel.loadNewsPage(id, page, size)
        }
        binding.refresh.setOnLoadMoreListener {
            page++
            viewModel.loadNewsPage(id, page, size)
        }
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        viewModel.newsPageLiveData.observe(viewLifecycleOwner, this::loaded)
        viewModel.loadNewsPage(id, page, size)
    }
    fun loaded(page:Page<News>) {
        this.page = page.current
        if (this.page == 1) {
            adapter.clear()
        }
        adapter += page.records
        binding.refresh.closeHeaderOrFooter()
    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { CategoryViewModel(requireContext().database.getNewsDao()) } }

}