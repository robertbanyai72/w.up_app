package com.robert.banyai.wup.data.cache

import com.robert.banyai.wup.data.model.CardDetailsResponse
import com.robert.banyai.wup.data.model.CardsResponse
import io.reactivex.rxjava3.core.Single

interface MemoryCacheManager {
    fun cacheCards(cardsResponse: CardsResponse): Single<CardsResponse>
    fun getCardById(id : String) : Single<CardDetailsResponse>
}