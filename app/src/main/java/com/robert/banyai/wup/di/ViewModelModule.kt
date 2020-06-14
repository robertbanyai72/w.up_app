package com.robert.banyai.wup.di

import androidx.lifecycle.ViewModel
import com.robert.banyai.wup.presentation.base.AssistedSavedStateViewModelFactory
import com.robert.banyai.wup.presentation.card.detail.CardDetailViewModel
import com.robert.banyai.wup.presentation.card.list.CardListViewModel
import com.robert.banyai.wup.presentation.main.MainViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(f: MainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(CardListViewModel::class)
    abstract fun bindCardListViewModel(f: CardListViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(CardDetailViewModel::class)
    abstract fun bindCardDetailViewModel(f: CardDetailViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
