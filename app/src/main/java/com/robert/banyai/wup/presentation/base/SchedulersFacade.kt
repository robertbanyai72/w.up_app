package com.robert.banyai.wup.presentation.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface SchedulersFacade {
    fun io(): Scheduler
    fun ui(): Scheduler
}

class SchedulersFacadeImpl : SchedulersFacade {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}