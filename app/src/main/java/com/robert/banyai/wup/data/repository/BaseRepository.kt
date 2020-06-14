package com.robert.banyai.wup.data.repository

import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.error.GenericException
import com.robert.banyai.wup.domain.error.NetworkException
import com.robert.banyai.wup.domain.model.BaseDomainModel
import io.reactivex.rxjava3.core.Single
import java.net.UnknownHostException

abstract class BaseRepository {

    fun <T : BaseDomainModel> Single<Resource<T>>.wrapDataLayerException(): Single<Resource<T>> {
        return onErrorResumeNext { error ->
            if (error is UnknownHostException) {
                return@onErrorResumeNext Single.just(Resource.error(NetworkException(message = error.message)))
            }

            return@onErrorResumeNext Single.just(Resource.error(
                GenericException(
                    message = error.message
                )
            ))
        }
    }
}