package com.robert.banyai.wup.di

import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.domain.usecase.GetCardUseCase
import com.robert.banyai.wup.domain.usecase.GetCardUseCaseImpl
import com.robert.banyai.wup.domain.usecase.GetCardsUseCase
import com.robert.banyai.wup.domain.usecase.GetCardsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCardsUseCase(cardRepository: CardRepository): GetCardsUseCase {
        return GetCardsUseCaseImpl(cardRepository)
    }

    @Provides
    fun provideGetCardUseCase(cardRepository: CardRepository): GetCardUseCase {
        return GetCardUseCaseImpl(cardRepository)
    }
}