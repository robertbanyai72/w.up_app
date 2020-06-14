package com.robert.banyai.wup.domain.error

class NetworkException(override val status: Int = 0, override val message: String? = "") : DataLayerException()