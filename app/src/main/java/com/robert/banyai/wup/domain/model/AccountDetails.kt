package com.robert.banyai.wup.domain.model

data class AccountDetails(
    val accountLimit: Int,
    val accountNumber: String
) : BaseDomainModel