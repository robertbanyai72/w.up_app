package com.robert.banyai.wup.domain.usecase

import com.robert.banyai.wup.common.happyCaseCardModel
import com.robert.banyai.wup.common.mock
import com.robert.banyai.wup.common.whenever
import com.robert.banyai.wup.data.repository.CardRepository
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.error.NetworkException
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.model.Card
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.util.*

class GetCardUseCaseTest {

    private val cardRepository: CardRepository = mock()
    private val getCardUseCase by lazy { GetCardUseCaseImpl(cardRepository) }

    @Test
    fun testGetCardUseCase_invoke_completed() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())

        whenever(cardRepository.getCard(getCardEvent))
            .thenReturn(Single.just(Resource.success(happyCaseCardModel())))

        getCardUseCase.invoke(getCardEvent)
            .test()
            .assertComplete()
    }

    @Test
    fun testGetCardUseCase_invoke_uncaught_error() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())
        val response = Throwable("Error response")

        whenever(cardRepository.getCard(getCardEvent))
            .thenReturn(Single.error(response))

        getCardUseCase.invoke(getCardEvent)
            .test()
            .assertError(response)
    }

    @Test
    fun testGetCardUseCase_invoke_caught_error() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())

        val error = Resource.error<Card>(NetworkException())
        whenever(cardRepository.getCard(getCardEvent))
            .thenReturn(Single.just(error))

        getCardUseCase.invoke(getCardEvent)
            .test()
            .assertValue(error)
    }

    @Test
    fun testGetCardUseCase_invoke_response() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())

        val success = Resource.success(happyCaseCardModel())

        whenever(cardRepository.getCard(getCardEvent))
            .thenReturn(Single.just(success))

        getCardUseCase.invoke(getCardEvent)
            .test()
            .assertValue(success)
    }
}