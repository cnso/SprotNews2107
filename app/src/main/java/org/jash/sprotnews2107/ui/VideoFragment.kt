package org.jash.sprotnews2107.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import org.jash.common.adapter.CommonAdapter
import org.jash.common.mvvm.BaseFragment
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.databinding.FragmentVideoBinding
import org.jash.sprotnews2107.entity.Page
import org.jash.sprotnews2107.entity.Video
import org.jash.sprotnews2107.viewmodel.VideoViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/video")
class VideoFragment : BaseFragment<FragmentVideoBinding, VideoViewModel>() {
    val adapter by lazy {CommonAdapter<Video>(R.layout.video_item, BR.video)}
    override fun initView() {
        binding.recycler.adapter = adapter
    }

    override fun initData() {
        viewModel.videoLiveData.observe(this, this::loaded)
        viewModel.loadVideo(1, 10)
    }
    fun loaded(page:Page<Video>) {
        adapter += page.records
    }

}