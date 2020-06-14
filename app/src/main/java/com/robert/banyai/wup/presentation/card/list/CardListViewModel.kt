package com.robert.banyai.wup.presentation.card.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.robert.banyai.wup.device.CurrencyFormatter
import com.robert.banyai.wup.device.DateFormatter
import com.robert.banyai.wup.device.ResourceManager
import com.robert.banyai.wup.domain.event.BaseEvent
import com.robert.banyai.wup.domain.event.GetCardsEvent
import com.robert.banyai.wup.domain.event.SelectCardEvent
import com.robert.banyai.wup.domain.model.Card
import com.robert.banyai.wup.domain.model.Status
import com.robert.banyai.wup.domain.usecase.GetCardsUseCase
import com.robert.banyai.wup.presentation.base.AssistedSavedStateViewModelFactory
import com.robert.banyai.wup.presentation.base.BaseViewModel
import com.robert.banyai.wup.presentation.base.SchedulersFacade
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class CardListViewModel @AssistedInject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val schedulersFacade: SchedulersFacade,
    private val resourceManager: ResourceManager,
    private val currencyFormatter: CurrencyFormatter,
    private val dateFormatter: DateFormatter,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel(schedulersFacade) {

    companion object {
        private const val cardIdStateKey = "cardIdStateKey"
        private const val cardResourceNamePrefix = "cccard"
    }

    //keeping it as map for performance purposes
    private val cards = HashMap<String, Card>()

    private val _fetchCardsStream: MutableLiveData<CardListUiModel?> =
        MutableLiveData()
    val fetchCardsStream: LiveData<CardListUiModel?> = _fetchCardsStream

    private val _selectedCardDetailStream: MutableLiveData<SelectedCardDetailUiModel?> =
        MutableLiveData()
    val selectedCardDetailStream: LiveData<SelectedCardDetailUiModel?> = _selectedCardDetailStream

    @AssistedInject.Factory
    interface Factory :
        AssistedSavedStateViewModelFactory<CardListViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): CardListViewModel
    }

    init {
        processEvent(GetCardsEvent())
    }

    override fun processEvent(event: BaseEvent) {
        when (event) {
            is GetCardsEvent -> {
                compositeDisposable.add(
                    getCardsUseCase.invoke(event)
                        .observeOn(schedulersFacade.ui())
                        .subscribeOn(schedulersFacade.io())
                        .doOnSubscribe { loadingStream.value = true }
                        .doAfterTerminate { loadingStream.value = false }
                        .subscribe({ resource ->
                            when (resource.status) {
                                Status.Success -> {
                                    val cardPagerAdapterItems = ArrayList<CardPagerAdapterItem>()

                                    resource.data?.cards?.forEach { card ->
                                        cards[card.cardId] = card
                                    }

                                    resource.data?.cards?.forEach { card ->
                                        cardPagerAdapterItems.add(
                                            CardPagerAdapterItem(
                                                cardId = card.cardId,
                                                resourceId = resourceManager.findDrawableResourceId(
                                                    cardResourceNamePrefix.plus(card.cardImage)
                                                )
                                            )
                                        )
                                    }

                                    _fetchCardsStream.value =
                                        CardListUiModel(cards = cardPagerAdapterItems)

                                    val item = if (savedStateHandle.contains(cardIdStateKey)) {
                                        resource.data?.cards?.find { card ->
                                            card.cardId == savedStateHandle.get<String>(
                                                cardIdStateKey
                                            )
                                        }
                                    } else {
                                        resource.data?.cards?.firstOrNull()
                                    }

                                    item?.let { card ->
                                        _selectedCardDetailStream.value = SelectedCardDetailUiModel(
                                            id = card.cardId,
                                            availableAmount = currencyFormatter.formatNumberByCurrency(
                                                card.availableBalance
                                            ),
                                            currentBalance = currencyFormatter.formatNumberByCurrency(
                                                card.currentBalance
                                            ),
                                            minPayment = currencyFormatter.formatNumberByCurrency(
                                                card.minPayment
                                            ),
                                            dueDate = dateFormatter.formatDate(card.dueDate),
                                            currency = card.currency,
                                            currentBalanceProgress = 1f - (card.availableBalance.toFloat() / (card.availableBalance + card.currentBalance)),
                                            hasAvailableAmount = card.availableBalance != 0,
                                            issuer = card.issuer
                                        )
                                    }
                                }

                                Status.Error -> {
                                    errorStream.value = resource.error?.message
                                }
                            }
                        }, {
                            errorStream.value = it.message
                        })
                )
            }

            is SelectCardEvent -> {
                savedStateHandle.set(cardIdStateKey, event.cardId)

                cards[event.cardId]?.let { card ->
                    _selectedCardDetailStream.value =
                        _selectedCardDetailStream.value?.copy(
                            id = card.cardId,
                            availableAmount = currencyFormatter.formatNumberByCurrency(card.availableBalance),
                            currentBalance = currencyFormatter.formatNumberByCurrency(card.currentBalance),
                            minPayment = currencyFormatter.formatNumberByCurrency(card.minPayment),
                            dueDate = dateFormatter.formatDate(card.dueDate),
                            currency = card.currency,
                            currentBalanceProgress = 1f - (card.availableBalance.toFloat() / (card.availableBalance + card.currentBalance)),
                            hasAvailableAmount = card.availableBalance != 0,
                            issuer = card.issuer
                        )
                }
            }
        }
    }
}