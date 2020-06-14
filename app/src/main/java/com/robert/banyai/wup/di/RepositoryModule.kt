package com.robert.banyai.wup.di

import com.robert.banyai.wup.data.cache.MemoryCacheManager
import com.robert.banyai.wup.data.clients.RestClient
import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.data.repository.CardRepositoryImpl
import com.robert.banyai.wup.domain.converter.CardConverter
import com.robert.banyai.wup.domain.converter.CardsConverter
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCardRepository(
        restClient: RestClient,
        cardsConverter: CardsConverter,
        memoryCacheManager: MemoryCacheManager,
        cardConverter: CardConverter
    ): CardRepository {
        return CardRepositoryImpl(restClient, cardsConverter, memoryCacheManager, cardConverter)
    }
}