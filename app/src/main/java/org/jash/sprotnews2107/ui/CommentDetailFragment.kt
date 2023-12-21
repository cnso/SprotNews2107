package org.jash.sprotnews2107.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.jash.common.adapter.CommonAdapter
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.FragmentCommentDetailBinding
import org.jash.sprotnews2107.entity.Comment

/**
 * A simple [Fragment] subclass.
 * Use the [CommentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentDetailFragment : BottomSheetDialogFragment() {
    var binding:FragmentCommentDetailBinding? = null
    val adapter by lazy { CommonAdapter<Comment>(R.layout.replay_item, BR.comment) }
    var comment:Comment? = null
        set(value) {
            field = value
            binding?.comment = value
            adapter.clear()
            adapter += value?.replays ?: listOf()
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentDetailBinding.inflate(inflater)
        binding?.comment = comment
        binding?.recycler?.adapter = adapter
        binding?.close?.setOnClickListener { dismiss() }
        return binding?.root
    }

}