package com.robert.banyai.wup.presentation.card.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.robert.banyai.wup.device.CurrencyFormatter
import com.robert.banyai.wup.device.DateFormatter
import com.robert.banyai.wup.device.StringFormatter
import com.robert.banyai.wup.domain.event.BaseEvent
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.domain.model.Status
import com.robert.banyai.wup.domain.usecase.GetCardUseCase
import com.robert.banyai.wup.presentation.base.AssistedSavedStateViewModelFactory
import com.robert.banyai.wup.presentation.base.BaseViewModel
import com.robert.banyai.wup.presentation.base.SchedulersFacade
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class CardDetailViewModel @AssistedInject constructor(
    private val getCardUseCase: GetCardUseCase,
    private val schedulersFacade: SchedulersFacade,
    private val currencyFormatter: CurrencyFormatter,
    private val dateFormatter: DateFormatter,
    private val stringFormatter: StringFormatter,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel(schedulersFacade) {

    companion object {
        private const val cardIdStateKey = "cardIdStateKey"
    }

    private val _cardDetailStream: MutableLiveData<CardDetailUiModel?> = MutableLiveData()
    val carDetailStream: LiveData<CardDetailUiModel?> = _cardDetailStream

    @AssistedInject.Factory
    interface Factory :
        AssistedSavedStateViewModelFactory<CardDetailViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): CardDetailViewModel
    }

    init {
        if (savedStateHandle.contains(cardIdStateKey)) {
            savedStateHandle.get<String>(cardIdStateKey)?.let { id ->
                processEvent(GetCardEvent(id))
            }
        }
    }

    override fun processEvent(event: BaseEvent) {
        when (event) {
            is GetCardEvent -> {
                savedStateHandle.set(cardIdStateKey, event.cardId)

                compositeDisposable.add(
                    getCardUseCase.invoke(event)
                        .observeOn(schedulersFacade.ui())
                        .subscribeOn(schedulersFacade.io())
                        .doOnSubscribe { loadingStream.value = true }
                        .doAfterTerminate { loadingStream.value = false }
                        .subscribe({ resource ->
                            when (resource.status) {
                                Status.Success -> {
                                    resource.data?.let { card ->
                                        val cardDetailUiModel =
                                            if (_cardDetailStream.value == null) {
                                                CardDetailUiModel()
                                            } else {
                                                _cardDetailStream.value
                                            }

                                        val totalAmount =
                                            card.availableBalance + card.currentBalance + card.reservations

                                        _cardDetailStream.value = cardDetailUiModel?.copy(
                                            cardId = card.cardId,
                                            currency = card.currency,
                                            currentBalance = currencyFormatter.formatNumberByCurrency(
                                                card.currentBalance
                                            ),
                                            availableBalance = currencyFormatter.formatNumberByCurrency(
                                                card.availableBalance
                                            ),
                                            reservations = currencyFormatter.formatNumberByCurrency(
                                                card.reservations
                                            ),
                                            balanceCarried = currencyFormatter.formatNumberByCurrency(
                                                card.balanceCarriedOverFromLastStatement
                                            ),
                                            totalSpending = currencyFormatter.formatNumberByCurrency(
                                                card.spendingsSinceLastStatement
                                            ),
                                            latestRePayment = dateFormatter.formatDate(card.yourLastRepayment),
                                            cardAccountLimit = currencyFormatter.formatNumberByCurrency(
                                                card.accountDetails.accountLimit
                                            ),
                                            cardAccountNumber = card.accountDetails.accountNumber,
                                            mainCardNumber = stringFormatter.formatCardNumber(card.cardNumber),
                                            mainCardHolderName = card.cardHolderName,
                                            supplementaryCardNumber = stringFormatter.formatCardNumber(
                                                card.cardNumber
                                            ),
                                            supplementaryCardHolderName = card.cardHolderName,
                                            currentBalanceProgress = (card.currentBalance.toFloat() / totalAmount),
                                            availableAmountProgress = (card.currentBalance.toFloat() / totalAmount) + (card.reservations.toFloat() / totalAmount)
                                        )
                                    }
                                }

                                Status.Error -> {
                                    errorStream.value = resource.error?.message
                                }
                            }
                        }, {

                        })
                )
            }
        }
    }

}