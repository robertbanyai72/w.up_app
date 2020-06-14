package com.robert.banyai.wup.presentation.card.detail

import com.robert.banyai.wup.presentation.base.UiModel

data class CardDetailUiModel(
    val cardId: String = "",
    val currency: String = "",
    val currentBalance: String = "",
    val availableBalance: String = "",
    val reservations: String = "",
    val balanceCarried: String = "",
    val totalSpending: String = "",
    val latestRePayment: String = "",
    val cardAccountLimit: String = "",
    val cardAccountNumber: String = "",
    val mainCardNumber: String = "",
    val mainCardHolderName: String = "",
    val supplementaryCardNumber: String = "",
    val supplementaryCardHolderName: String = "",
    val currentBalanceProgress: Float = 0f,
    val availableAmountProgress: Float = 0f
) : UiModel