package org.jash.sprotnews2107.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import org.jash.common.mvvm.BaseActivity
import org.jash.common.mvvm.BaseFragment
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.adapter.CategoryAdapter
import org.jash.sprotnews2107.databinding.FragmentNewsBinding
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.viewmodel.NewsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/news")
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    val adapter by lazy { CategoryAdapter(this) }
    override fun initView() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

    override fun initData() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner, this::loaded)
        viewModel.loadCategory()
    }
    fun loaded(categories:List<Category>) {
        adapter += categories
    }


}