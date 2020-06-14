package com.robert.banyai.wup.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<I : AdapterItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapterItem: I)
}