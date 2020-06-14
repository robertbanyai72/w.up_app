package com.robert.banyai.wup.device

import javax.inject.Inject

class StringFormatter @Inject constructor() {

    fun formatCardNumber(cardNumber: String): String {

        val sb = StringBuilder(cardNumber)
        for (i in 0 until sb.length - 4) {
            if (Character.isDigit(sb[i])) {
                sb.setCharAt(i, '*')
            }
        }
        return sb.toString()
    }
}