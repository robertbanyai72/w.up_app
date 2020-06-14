package com.robert.banyai.wup.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountDetailsResponse(
    val accountLimit: Int = 0,
    val accountNumber: String = ""
) : BaseDataModel