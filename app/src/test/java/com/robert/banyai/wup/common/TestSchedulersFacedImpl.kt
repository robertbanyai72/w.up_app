package com.robert.banyai.wup.common

import com.robert.banyai.wup.presentation.base.SchedulersFacade
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TestSchedulersFacedImpl : SchedulersFacade {

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}