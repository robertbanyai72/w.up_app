package com.robert.banyai.wup.domain

import com.robert.banyai.wup.domain.model.Status

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?) =
            Resource(Status.Success, data, null)

        fun <T> error(error: Throwable, data: T? = null) =
            Resource(Status.Error, data, error)
    }
}