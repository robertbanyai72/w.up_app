package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.CardsResponse
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.model.CardList
import javax.inject.Inject

class CardsConverter @Inject constructor(private val cardConverter: CardConverter) :
    BaseConverter<CardList, CardsResponse> {

    override fun convertToDomain(dataModel: CardsResponse): CardList {
        val cards = ArrayList<Card>()

        dataModel.cards.forEach { cardItemResponse ->
            cards.add(
                cardConverter.convertToDomain(cardItemResponse)
            )
        }
        return CardList(cards = cards)
    }
}