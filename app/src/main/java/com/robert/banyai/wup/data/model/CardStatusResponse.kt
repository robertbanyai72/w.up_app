package com.robert.banyai.wup.data.model

import com.squareup.moshi.Json

enum class CardStatusResponse : BaseDataModel {
    @field:Json(name = "ACTIVE")
    Active,

    @field:Json(name = "BLOCKED")
    Blocked,

    Unknown
}