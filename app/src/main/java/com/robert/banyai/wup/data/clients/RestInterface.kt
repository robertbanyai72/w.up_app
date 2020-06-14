package com.robert.banyai.wup.data.clients

import com.robert.banyai.wup.data.model.CardDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RestInterface {

    @GET("api/v1/cards.json")
    fun fetchCards(): Single<List<CardDetailsResponse>>
}