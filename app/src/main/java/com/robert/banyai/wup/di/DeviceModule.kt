package com.robert.banyai.wup.di

import com.robert.banyai.wup.presentation.base.SchedulersFacade
import com.robert.banyai.wup.presentation.base.SchedulersFacadeImpl
import dagger.Module
import dagger.Provides
import java.text.NumberFormat

@Module
class DeviceModule {

    @Provides
    fun provideNumberFormat(): NumberFormat {
        return NumberFormat.getNumberInstance()
    }

    @Provides
    fun provideSchedulersFaced(): SchedulersFacade {
        return SchedulersFacadeImpl()
    }
}