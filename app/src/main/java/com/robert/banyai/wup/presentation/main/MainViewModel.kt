package com.robert.banyai.wup.presentation.main

import androidx.lifecycle.SavedStateHandle
import com.robert.banyai.wup.domain.event.BaseEvent
import com.robert.banyai.wup.presentation.base.AssistedSavedStateViewModelFactory
import com.robert.banyai.wup.presentation.base.BaseViewModel
import com.robert.banyai.wup.presentation.base.SchedulersFacade
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
    schedulersFacade: SchedulersFacade,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel(schedulersFacade) {

    @AssistedInject.Factory
    interface Factory :
        AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    override fun processEvent(event: BaseEvent) {

    }
}