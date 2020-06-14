package com.robert.banyai.wup.presentation.card.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.robert.banyai.wup.common.TestSchedulersFacedImpl
import com.robert.banyai.wup.common.happyCaseCardModel
import com.robert.banyai.wup.common.mock
import com.robert.banyai.wup.common.whenever
import com.robert.banyai.wup.device.CurrencyFormatter
import com.robert.banyai.wup.device.DateFormatter
import com.robert.banyai.wup.device.StringFormatter
import com.robert.banyai.wup.domain.Resource
import com.robert.banyai.wup.domain.error.NetworkException
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.usecase.GetCardUseCase
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


class CardDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getCardUseCase = mock<GetCardUseCase>()
    private val schedulersFacade = TestSchedulersFacedImpl()
    private val currencyFormatter = CurrencyFormatter()
    private val dateFormatter = DateFormatter()
    private val stringFormatter = StringFormatter()
    private val savedStateHandle = SavedStateHandle()

    val viewModel by lazy {
        CardDetailViewModel(
            getCardUseCase,
            schedulersFacade,
            currencyFormatter,
            dateFormatter,
            stringFormatter,
            savedStateHandle
        )
    }

    @Before
    fun initTest() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCardDetailViewModel_getCard_response() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())

        val success = Resource.success(happyCaseCardModel())

        whenever(getCardUseCase.invoke(getCardEvent))
            .thenReturn(Single.just(success))

        val observer = mock<Observer<CardDetailUiModel?>>()

        viewModel.carDetailStream.observeForever(observer)

        viewModel.processEvent(getCardEvent)

        ArgumentCaptor.forClass(CardDetailUiModel::class.java).run {
            verify(observer).onChanged(capture())
        }
    }

    @Test
    fun testCardDetailViewModel_getCard_Error() {
        val getCardEvent = GetCardEvent(UUID.randomUUID().toString())

        val error = Resource.error<Card>(NetworkException(message = "Network error occurred"))
        whenever(getCardUseCase.invoke(getCardEvent))
            .thenReturn(Single.just(error))

        val observer = mock<Observer<String?>>()

        viewModel.errorStream.observeForever(observer)

        viewModel.processEvent(getCardEvent)

        ArgumentCaptor.forClass(String::class.java).run {
            verify(observer).onChanged(capture())
            assertEquals(allValues.first(), error.error?.message)
        }
    }
}