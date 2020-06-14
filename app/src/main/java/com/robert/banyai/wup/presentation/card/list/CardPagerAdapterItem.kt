package com.robert.banyai.wup.presentation.card.list

import com.robert.banyai.wup.presentation.base.AdapterItem

data class CardPagerAdapterItem(
    val cardId: String,
    val resourceId: Int
) : AdapterItem {

    override fun uniqueIdentifier(): Any {
        return resourceId
    }
}