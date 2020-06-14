package com.robert.banyai.wup.presentation.card.list

data class SelectedCardDetailUiModel(
    val id: String = "",
    val issuer: String = "",
    val availableAmount: String = "",
    val currentBalance: String = "",
    val minPayment: String = "",
    val dueDate: String = "",
    val currency: String = "",
    val currentBalanceProgress: Float = 0f,
    val hasAvailableAmount: Boolean = false
)