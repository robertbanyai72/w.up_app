package com.robert.banyai.wup.di

import com.robert.banyai.wup.data.cache.MemoryCacheManager
import com.robert.banyai.wup.data.cache.MemoryCacheManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideMemoryCacheManager(): MemoryCacheManager {
        return MemoryCacheManagerImpl()
    }

}