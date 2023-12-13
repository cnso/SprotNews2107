package org.jash.sprotnews2107.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.ui.CategoryFragment

class CategoryAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    val data = mutableListOf<Category>()
    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment = ARouter.getInstance()
        .build("/news/category").withInt("id", data[position].id).navigation() as Fragment

    operator fun plusAssign(list:List<Category>) {
        val size = data.size
        data += list
        notifyItemRangeInserted(size, list.size)
    }
    fun getTitle(position: Int):String = data[position].name
}