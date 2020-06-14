package com.robert.banyai.wup.data.cache

import com.robert.banyai.wup.data.model.CardDetailsResponse
import com.robert.banyai.wup.data.model.CardsResponse
import io.reactivex.rxjava3.core.Single

class MemoryCacheManagerImpl : MemoryCacheManager {

    private val cards = HashMap<String, CardDetailsResponse>()

    override fun cacheCards(cardsResponse: CardsResponse): Single<CardsResponse> {
        return Single.fromCallable {
            cardsResponse.cards.forEach { card -> this.cards[card.cardId] = card }
            cardsResponse
        }
    }
    override fun getCardById(id: String): Single<CardDetailsResponse> {
        return Single.fromCallable {
            cards[id]
        }
    }
}