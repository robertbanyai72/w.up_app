package com.robert.banyai.wup.device

import junit.framework.Assert.assertEquals
import org.junit.Test

class StringFormatterTest {

    private val stringFormatter = StringFormatter()

    @Test
    fun testFormatCardNumberFourTagCase() {
        val cardNumber = "1234-1234-1234-1234"
        val expectedCardNumber = "****-****-****-1234"

        assertEquals(expectedCardNumber, stringFormatter.formatCardNumber(cardNumber))
    }

    @Test
    fun testFormatCardNumberEmptyTagCase() {
        val cardNumber = ""
        val expectedCardNumber = ""

        assertEquals(expectedCardNumber, stringFormatter.formatCardNumber(cardNumber))
    }

    @Test
    fun testFormatCardNumberOnlyDigitCase() {
        val cardNumber = "12345678910"
        val expectedCardNumber = "*******8910"

        assertEquals(expectedCardNumber, stringFormatter.formatCardNumber(cardNumber))
    }
}