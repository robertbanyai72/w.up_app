package com.robert.banyai.wup.di

import android.content.Context
import com.robert.banyai.wup.WupApp
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    fun provideContext(application: WupApp): Context {
        return application.applicationContext
    }
}
