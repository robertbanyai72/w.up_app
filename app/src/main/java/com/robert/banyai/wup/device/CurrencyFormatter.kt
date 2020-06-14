package com.robert.banyai.wup.device

import java.text.DecimalFormat
import javax.inject.Inject

class CurrencyFormatter @Inject constructor() {

    fun formatNumberByCurrency(value: Int): String {
        return DecimalFormat("###,###,##0.00").format(value).replace(',', '’')
    }
}