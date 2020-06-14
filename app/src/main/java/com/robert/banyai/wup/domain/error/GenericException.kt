package com.robert.banyai.wup.domain.error

class GenericException(override val status: Int = 0, override val message: String? = "") :
    DataLayerException()