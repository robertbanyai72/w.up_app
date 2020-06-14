package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.AccountDetailsResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class AccountDetailsConverterTest {

    private val accountDetailsConverter = AccountDetailsConverter()

    @Test
    fun testConvertToDomain() {
        val accountNumber = "19202020"
        val accountLimit = 10000

        val dataModel = AccountDetailsResponse(
            accountLimit = accountLimit,
            accountNumber = accountNumber
        )

        accountDetailsConverter.convertToDomain(dataModel).apply {
            assertEquals(accountNumber, this.accountNumber)
            assertEquals(accountLimit, this.accountLimit)
        }
    }
}