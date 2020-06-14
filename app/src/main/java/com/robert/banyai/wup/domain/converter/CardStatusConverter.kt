package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.CardStatusResponse
import com.robert.banyai.wup.domain.model.CardStatus
import javax.inject.Inject

class CardStatusConverter @Inject constructor() : BaseConverter<CardStatus, CardStatusResponse> {

    override fun convertToDomain(dataModel: CardStatusResponse): CardStatus {
        return when (dataModel) {
            CardStatusResponse.Active -> CardStatus.Active
            CardStatusResponse.Blocked -> CardStatus.Blocked
            CardStatusResponse.Unknown -> CardStatus.Unknown
        }
    }

}