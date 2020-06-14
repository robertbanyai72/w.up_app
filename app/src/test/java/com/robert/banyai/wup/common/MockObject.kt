package com.robert.banyai.wup.common

import com.robert.banyai.wup.domain.model.AccountDetails
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.model.CardList
import com.robert.banyai.wup.domain.model.CardStatus

fun happyCaseCardModel(): Card {
    return Card(
        cardId = "1",
        issuer = "Visa - Banca Monte Dei Paschi Di Siena S.P.A., Italy (Electron)",
        cardNumber = "4003-1565-5402-0882",
        expirationDate = "2021/12",
        cardHolderName = "Mr. Shea Wiza",
        friendlyName = "Personal Loan Account",
        currency = "BAM",
        cvv = "962",
        availableBalance = 60456,
        currentBalance = 4544,
        minPayment = 0,
        dueDate = "2018-09-26",
        reservations = 3688,
        balanceCarriedOverFromLastStatement = 856,
        spendingsSinceLastStatement = 3688,
        yourLastRepayment = "2018-09-26",
        accountDetails = AccountDetails(accountLimit = 65000, accountNumber = "01-558-9"),
        status = CardStatus.Active,
        cardImage = "1"
    )
}

fun happyCaseCardsModel(): CardList {
    val cards = ArrayList<Card>()

    for (i in 0..3) {
        cards.add(happyCaseCardModel())
    }

    return CardList(cards)
}