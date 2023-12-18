package org.jash.sprotnews2107.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alibaba.android.arouter.launcher.ARouter
import com.youth.banner.adapter.BannerAdapter
import org.jash.sprotnews2107.R

class SplashAdapter(data:List<Int>):BannerAdapter<Int, SplashViewHolder>(data) {
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): SplashViewHolder =
        SplashViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.banner_image, parent, false))
    override fun onBindView(holder: SplashViewHolder?, data: Int?, position: Int, size: Int) {
        holder?.btn?.isVisible = position == size - 1
        holder?.btn?.setOnClickListener {
            ARouter.getInstance()
                .build("/news/main")
                .navigation()
            ((it.context as ContextThemeWrapper).baseContext as Activity).finish()
        }
        holder?.image?.setImageResource(data ?: 0)
    }
}
class SplashViewHolder(itemView:View):ViewHolder(itemView) {
    val btn:Button
    val image:ImageView
    init {
        btn = itemView.findViewById(R.id.btn)
        image = itemView.findViewById(R.id.image)
    }
}