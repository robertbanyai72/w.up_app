package com.robert.banyai.wup.data.clients

import com.robert.banyai.wup.data.model.CardsResponse
import io.reactivex.rxjava3.core.Single

interface RestClient {
    fun fetchCards(): Single<CardsResponse>
}