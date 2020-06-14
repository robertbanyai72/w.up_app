package com.robert.banyai.wup.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsResponse(val cards: List<CardDetailsResponse>) :
    BaseDataModel