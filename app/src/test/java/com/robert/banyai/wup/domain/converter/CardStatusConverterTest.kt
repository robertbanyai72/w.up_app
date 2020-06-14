package com.robert.banyai.wup.domain.converter

import com.robert.banyai.wup.data.model.CardStatusResponse
import com.robert.banyai.wup.domain.model.CardStatus
import junit.framework.Assert.assertEquals
import org.junit.Test

class CardStatusConverterTest {

    private val cardStatusConverter = CardStatusConverter()

    @Test
    fun testConvertToDomain() {
        val dataModel = CardStatusResponse.Blocked
        val domainModel = CardStatus.Blocked

        cardStatusConverter.convertToDomain(dataModel).apply {
            assertEquals(domainModel, this)
        }
    }
}