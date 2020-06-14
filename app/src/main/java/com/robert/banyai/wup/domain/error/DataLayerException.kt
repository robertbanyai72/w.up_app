package com.robert.banyai.wup.domain.error

open class DataLayerException(open val status : Int = 0, override val message : String? = "") : Throwable()