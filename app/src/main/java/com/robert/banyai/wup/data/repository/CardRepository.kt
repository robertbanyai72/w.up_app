package com.robert.banyai.wup.data.repository

import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.event.GetCardsEvent
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.model.CardList
import io.reactivex.rxjava3.core.Single

interface CardRepository {
    fun fetchCards(getCardsEvent: GetCardsEvent): Single<Resource<CardList>>
    fun getCard(getCardEvent: GetCardEvent): Single<Resource<Card>>
}