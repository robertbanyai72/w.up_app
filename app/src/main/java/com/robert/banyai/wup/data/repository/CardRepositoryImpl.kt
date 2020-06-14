package com.robert.banyai.wup.data.repository

import com.robert.banyai.wup.data.cache.MemoryCacheManager
import com.robert.banyai.wup.data.clients.RestClient
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.converter.CardConverter
import com.robert.banyai.wup.domain.converter.CardsConverter
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.event.GetCardsEvent
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.model.CardList
import io.reactivex.rxjava3.core.Single

class CardRepositoryImpl(
    private val restClient: RestClient,
    private val cardsConverter: CardsConverter,
    private val memoryCacheManager: MemoryCacheManager,
    private val cardConverter: CardConverter
) : BaseRepository(), CardRepository {

    override fun fetchCards(getCardsEvent: GetCardsEvent): Single<Resource<CardList>> {
        return restClient.fetchCards()
            .flatMap {
                memoryCacheManager.cacheCards(it)
            }
            .map { cardsResponse ->
                cardsConverter.convertToDomain(cardsResponse)
            }
            .map { cardList ->
                Resource.success(cardList)
            }
            .wrapDataLayerException()
    }

    override fun getCard(getCardEvent: GetCardEvent): Single<Resource<Card>> {
        return memoryCacheManager.getCardById(getCardEvent.cardId)
            .map { cardDetailResponse ->
                cardConverter.convertToDomain(cardDetailResponse)
            }
            .map { card ->
                Resource.success(card)
            }
            .wrapDataLayerException()
    }
}