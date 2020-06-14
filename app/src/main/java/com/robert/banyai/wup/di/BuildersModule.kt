package com.robert.banyai.wup.di

import com.robert.banyai.wup.presentation.card.detail.CardDetailFragment
import com.robert.banyai.wup.presentation.card.list.CardListFragment
import com.robert.banyai.wup.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun launcherMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun launcherCardListFragment(): CardListFragment

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun launcherCardDetailFragment(): CardDetailFragment
}