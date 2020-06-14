package com.robert.banyai.wup.presentation.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<V : BaseViewHolder<I>, I : AdapterItem> : RecyclerView.Adapter<V>() {

    protected val data = AsyncListDiffer(this,
        object : DiffUtil.ItemCallback<I>() {
            override fun areItemsTheSame(oldItem: I, newItem: I): Boolean {
                return oldItem.uniqueIdentifier() == newItem.uniqueIdentifier()
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: I, newItem: I): Boolean {
                return oldItem == newItem
            }
        })

    fun setData(mData: List<I>) {
        data.submitList(mData)
    }

    fun getData(): List<I> {
        return data.currentList
    }

    protected abstract fun setViewHolder(parent: ViewGroup, viewType: Int): V

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return setViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(data.currentList[position])
    }

    override fun getItemCount(): Int {
        return data.currentList.size
    }
}
