package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.CardDetailsResponse
import com.robert.banyai.wup.domain.model.Card
import javax.inject.Inject

class CardConverter @Inject constructor(
    private val accountDetailsConverter: AccountDetailsConverter,
    private val cardStatusConverter: CardStatusConverter
) : BaseConverter<Card, CardDetailsResponse> {

    override fun convertToDomain(dataModel: CardDetailsResponse): Card {
        return Card(
            cardId = dataModel.cardId,
            issuer = dataModel.issuer,
            cardNumber = dataModel.cardNumber,
            expirationDate = dataModel.expirationDate,
            cardHolderName = dataModel.cardHolderName,
            friendlyName = dataModel.friendlyName,
            currency = dataModel.currency,
            cvv = dataModel.cvv,
            availableBalance = dataModel.availableBalance,
            currentBalance = dataModel.currentBalance,
            minPayment = dataModel.minPayment,
            dueDate = dataModel.dueDate,
            reservations = dataModel.reservations,
            balanceCarriedOverFromLastStatement = dataModel.balanceCarriedOverFromLastStatement,
            spendingsSinceLastStatement = dataModel.spendingsSinceLastStatement,
            yourLastRepayment = dataModel.yourLastRepayment,
            accountDetails = accountDetailsConverter.convertToDomain(dataModel.accountDetailsResponse),
            status = cardStatusConverter.convertToDomain(dataModel.statusResponse),
            cardImage = dataModel.cardImage
        )
    }
}
