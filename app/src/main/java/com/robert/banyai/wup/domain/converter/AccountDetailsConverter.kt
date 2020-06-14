package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.AccountDetailsResponse
import com.robert.banyai.wup.domain.model.AccountDetails
import javax.inject.Inject

class AccountDetailsConverter @Inject constructor() :
    BaseConverter<AccountDetails, AccountDetailsResponse> {

    override fun convertToDomain(dataModel: AccountDetailsResponse): AccountDetails {
        return AccountDetails(
            accountLimit = dataModel.accountLimit,
            accountNumber = dataModel.accountNumber
        )
    }

}