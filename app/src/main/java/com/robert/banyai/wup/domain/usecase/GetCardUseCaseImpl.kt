package com.robert.banyai.wup.domain.usecase

import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.model.Card
import io.reactivex.rxjava3.core.Single

interface GetCardUseCase : BaseUseCase<GetCardEvent, Card>

class GetCardUseCaseImpl(private val cardRepository: CardRepository) : GetCardUseCase {

    override fun invoke(eventModel: GetCardEvent): Single<Resource<Card>> {
        return cardRepository.getCard(eventModel)
    }
}