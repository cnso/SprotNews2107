package org.jash.sprotnews2107.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseFragment
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.FragmentCategoryBinding
import org.jash.sprotnews2107.viewmodel.CategoryViewModel


@Route(path = "/news/category")
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    @Autowired
    @JvmField
    var id:Int = 0
    override fun initView() {
    }

    override fun initData() {
        ARouter.getInstance().inject(this)
        binding.tv.text = "分类id:$id"
    }

}