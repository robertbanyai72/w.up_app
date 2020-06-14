package com.robert.banyai.wup.domain.usecase

import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.event.BaseEvent
import com.robert.banyai.wup.domain.model.BaseDomainModel
import io.reactivex.rxjava3.core.Single

interface BaseUseCase<EventModel : BaseEvent, DomainModel : BaseDomainModel> {
    operator fun invoke(eventModel: EventModel): Single<Resource<DomainModel>>
}