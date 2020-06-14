package com.robert.banyai.wup.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robert.banyai.wup.domain.event.BaseEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel(
    schedulersFacade: SchedulersFacade
) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val eventStream: PublishSubject<BaseEvent> = PublishSubject.create()
    val loadingStream: MutableLiveData<Boolean> = MutableLiveData()
    val errorStream: MutableLiveData<String?> = MutableLiveData()

    init {
        compositeDisposable.add(eventStream
            .observeOn(schedulersFacade.ui())
            .subscribeOn(schedulersFacade.io())
            .subscribe { event ->
                processEvent(event)
            })
    }

    abstract fun processEvent(event: BaseEvent)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}