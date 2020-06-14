package com.robert.banyai.wup.data.clients

import com.robert.banyai.wup.data.model.CardsResponse
import io.reactivex.rxjava3.core.Single

class RestClientImpl(
    private val restInterface: RestInterface
) : RestClient {

    //minor conversion on the data response to be able to use the base converter,
    // maybe a custom moshi converter could be more efficient,
    // but based on the time, it was the easiest solution
    override fun fetchCards(): Single<CardsResponse> {
        return restInterface.fetchCards()
            .map {
                CardsResponse(it)
            }
    }
}