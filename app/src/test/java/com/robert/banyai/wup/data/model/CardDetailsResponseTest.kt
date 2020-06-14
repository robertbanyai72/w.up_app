package com.robert.banyai.wup.data.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CardDetailsResponseTest {

    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Test
    fun testMoshiParseHappyCaseObject() {
        val jsonAdapter = moshi.adapter(CardDetailsResponse::class.java)

        val data = jsonAdapter.fromJson(
            "{\n" +
                    "    \"cardId\": \"1\",\n" +
                    "    \"issuer\": \"Visa - Banca Monte Dei Paschi Di Siena S.P.A., Italy (Electron)\",\n" +
                    "    \"cardNumber\": \"4003-1565-5402-0882\",\n" +
                    "    \"expirationDate\": \"2021/12\",\n" +
                    "    \"cardHolderName\": \"Mr. Shea Wiza\",\n" +
                    "    \"friendlyName\": \"Personal Loan Account\",\n" +
                    "    \"currency\": \"BAM\",\n" +
                    "    \"cvv\": \"962\",\n" +
                    "    \"availableBalance\": 60456,\n" +
                    "    \"currentBalance\": 4544,\n" +
                    "    \"minPayment\": 0,\n" +
                    "    \"dueDate\": \"2018-09-26\",\n" +
                    "    \"reservations\": 3688,\n" +
                    "    \"balanceCarriedOverFromLastStatement\": 856,\n" +
                    "    \"spendingsSinceLastStatement\": 3688,\n" +
                    "    \"yourLastRepayment\": \"2018-09-26\",\n" +
                    "    \"accountDetails\": {\n" +
                    "        \"accountLimit\": 65000,\n" +
                    "        \"accountNumber\": \"01-558-9\"\n" +
                    "    },\n" +
                    "    \"status\": \"ACTIVE\",\n" +
                    "    \"cardImage\": \"1\"\n" +
                    "}"
        )

        assertNotNull(data)
        assertNotNull(data?.statusResponse)
        assertNotNull(data?.accountDetailsResponse)
        assertEquals(data?.cardHolderName, "Mr. Shea Wiza")
        assertEquals(data?.statusResponse, CardStatusResponse.Active)
    }

    @Test
    fun testMoshiParseNullObjects() {
        val jsonAdapter = moshi.adapter(CardDetailsResponse::class.java)

        val data = jsonAdapter.fromJson(
            "{\n" +
                    "\t\"cardId\": \"1\",\n" +
                    "\t\"issuer\": \"Visa - Banca Monte Dei Paschi Di Siena S.P.A., Italy (Electron)\",\n" +
                    "\t\"cardNumber\": \"4003-1565-5402-0882\",\n" +
                    "\t\"expirationDate\": \"2021/12\",\n" +
                    "\t\"cardHolderName\": \"Mr. Shea Wiza\",\n" +
                    "\t\"friendlyName\": \"Personal Loan Account\",\n" +
                    "\t\"currency\": \"BAM\",\n" +
                    "\t\"cvv\": \"962\",\n" +
                    "\t\"availableBalance\": 1000,\n" +
                    "\t\"currentBalance\": 4544,\n" +
                    "\t\"minPayment\": 0,\n" +
                    "\t\"dueDate\": \"2018-09-26\",\n" +
                    "\t\"reservations\": 3688,\n" +
                    "\t\"balanceCarriedOverFromLastStatement\": 856,\n" +
                    "\t\"spendingsSinceLastStatement\": 3688,\n" +
                    "\t\"yourLastRepayment\": \"2018-09-26\",\n" +
                    "\t\"accountDetails\": null,\n" +
                    "\t\"status\": null,\n" +
                    "\t\"cardImage\": \"1\"\n" +
                    "}"
        )

        assertNotNull(data)
        assertNotNull(data?.accountDetailsResponse)
        assertEquals(data?.statusResponse, CardStatusResponse.Unknown)
    }
}