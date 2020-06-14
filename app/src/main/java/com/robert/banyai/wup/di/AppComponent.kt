package com.robert.banyai.wup.di

import com.robert.banyai.wup.WupApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (BuildersModule::class),
        (NetworkModule::class),
        (RepositoryModule::class),
        (DeviceModule::class),
        (CacheModule::class),
        (UseCaseModule::class),
        (AppModule::class)]
)

interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: WupApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: WupApp): Builder

        fun build(): AppComponent
    }
}
