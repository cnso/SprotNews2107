package org.jash.sprotnews2107.adapter

import org.jash.common.adapter.CommonAdapter
import org.jash.common.adapter.CommonViewHolder
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.BR
import org.jash.sprotnews2107.databinding.CommentItemBinding
import org.jash.sprotnews2107.entity.Comment

class CommentAdapter(val replay:(Comment) -> Unit, val openDetail:(Comment) -> Unit):CommonAdapter<Comment>(R.layout.comment_item, BR.comment) {
    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.binding as CommentItemBinding
        val comment = data[position]
        binding.replay.setOnClickListener {
            replay(comment)
        }
        binding.openDetail.setOnClickListener {
            openDetail(comment)
        }
    }
}