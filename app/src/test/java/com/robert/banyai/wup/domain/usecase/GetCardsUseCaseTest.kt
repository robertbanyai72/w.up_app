package com.robert.banyai.wup.domain.usecase

import com.robert.banyai.wup.common.happyCaseCardsModel
import com.robert.banyai.wup.common.mock
import com.robert.banyai.wup.common.whenever
import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.error.NetworkException
import com.robert.banyai.wup.domain.event.GetCardsEvent
import com.robert.banyai.wup.domain.model.CardList
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetCardsUseCaseTest {

    private val cardRepository: CardRepository = mock()
    private val getCardsUseCase by lazy { GetCardsUseCaseImpl(cardRepository) }

    @Test
    fun testGetCardsUseCase_invoke_completed() {
        val getCardsEvent = GetCardsEvent()

        whenever(cardRepository.fetchCards(getCardsEvent))
            .thenReturn(Single.just(Resource.success(happyCaseCardsModel())))

        getCardsUseCase.invoke(getCardsEvent)
            .test()
            .assertComplete()
    }

    @Test
    fun testGetCardsUseCase_invoke_uncaught_error() {
        val getCardsEvent = GetCardsEvent()
        val response = Throwable("Uncaught error")

        whenever(cardRepository.fetchCards(getCardsEvent))
            .thenReturn(Single.error(response))

        getCardsUseCase.invoke(getCardsEvent)
            .test()
            .assertError(response)
    }

    @Test
    fun testGetCardsUseCase_invoke_caught_error() {
        val getCardsEvent = GetCardsEvent()

        val error = Resource.error<CardList>(NetworkException())

        whenever(cardRepository.fetchCards(getCardsEvent))
            .thenReturn(Single.just(error))

        getCardsUseCase.invoke(getCardsEvent)
            .test()
            .assertValue(error)
    }

    @Test
    fun testGetCardsUseCase_invoke_response() {
        val getCardsEvent = GetCardsEvent()

        val success = Resource.success(happyCaseCardsModel())

        whenever(cardRepository.fetchCards(getCardsEvent))
            .thenReturn(Single.just(success))

        getCardsUseCase.invoke(getCardsEvent)
            .test()
            .assertValue(success)
    }
}