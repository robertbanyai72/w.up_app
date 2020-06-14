package com.robert.banyai.wup.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//I assume that strings and ints cannot be null, other case I would write a custom moshi adapter that can resolve nullability for both
@JsonClass(generateAdapter = true)
data class CardDetailsResponse(
    val cardId: String,
    val issuer: String,
    val cardNumber: String,
    val expirationDate: String,
    val cardHolderName: String,
    val friendlyName: String,
    val currency: String,
    val cvv: String,
    val availableBalance: Int,
    val currentBalance: Int,
    val minPayment: Int,
    val dueDate: String,
    val reservations: Int,
    val balanceCarriedOverFromLastStatement: Int,
    val spendingsSinceLastStatement: Int,
    val yourLastRepayment: String,
    @field:Json(name = "accountDetails") val _accountDetailsResponse: AccountDetailsResponse?,
    @field:Json(name = "status") val _statusResponse: CardStatusResponse?,
    val cardImage: String
) : BaseDataModel {

    val accountDetailsResponse
        get() = _accountDetailsResponse ?: AccountDetailsResponse()
    val statusResponse
        get() = _statusResponse ?: CardStatusResponse.Unknown
}