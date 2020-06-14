package com.robert.banyai.wup.presentation.card.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robert.banyai.wup.R
import com.robert.banyai.wup.presentation.base.BaseAdapter
import com.robert.banyai.wup.presentation.base.BaseViewHolder
import kotlinx.android.synthetic.main.view_card_item.view.*
import javax.inject.Inject

class CardPagerViewHolder(itemView: View) : BaseViewHolder<CardPagerAdapterItem>(itemView) {

    override fun bind(adapterItem: CardPagerAdapterItem) {
        itemView.imageViewCard.setImageResource(adapterItem.resourceId)
    }
}

class CardPagerAdapter @Inject constructor() :
    BaseAdapter<CardPagerViewHolder, CardPagerAdapterItem>() {

    override fun setViewHolder(parent: ViewGroup, viewType: Int): CardPagerViewHolder {
        return CardPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_card_item, parent, false)
        )
    }

}