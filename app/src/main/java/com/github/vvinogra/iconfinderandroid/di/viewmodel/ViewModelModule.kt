package com.github.vvinogra.iconfinderandroid.di.viewmodel

import androidx.lifecycle.ViewModel
import com.github.vvinogra.iconfinderandroid.ui.filters.viewmodel.FiltersViewModel
import com.github.vvinogra.iconfinderandroid.ui.searchicons.viewmodel.SearchIconsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchIconsViewModel::class)
    abstract fun provideSearchIconsViewModel(searchIconsViewModel: SearchIconsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    abstract fun provideFiltersViewModel(filtersViewModel: FiltersViewModel): ViewModel
}