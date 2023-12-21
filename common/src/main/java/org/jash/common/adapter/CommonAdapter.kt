package org.jash.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class CommonAdapter<D>(val layoutId:Int, val variableId:Int, val listener:((d:D) ->Unit)? = null):Adapter<CommonViewHolder>() {
    val data = mutableListOf<D>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder =
        CommonViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val d = data[position]
        holder.binding.setVariable(variableId, d)
        listener?.let {
            holder.itemView.setOnClickListener { it(d) }
        }
    }
    operator fun plusAssign(list:List<D>) {
        val size = data.size
        data += list
        notifyItemRangeInserted(size, list.size)
    }
    fun clear() {
        val size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }
}
class CommonViewHolder(val binding: ViewDataBinding):ViewHolder(binding.root)