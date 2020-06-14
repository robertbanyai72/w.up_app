package com.robert.banyai.wup.domain.usecase

import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.event.GetCardsEvent
import com.robert.banyai.wup.domain.model.CardList
import io.reactivex.rxjava3.core.Single

interface GetCardsUseCase : BaseUseCase<GetCardsEvent, CardList>

class GetCardsUseCaseImpl(private val cardRepository: CardRepository) : GetCardsUseCase {

    override fun invoke(eventModel: GetCardsEvent): Single<Resource<CardList>> {
        return cardRepository.fetchCards(eventModel)
    }
}