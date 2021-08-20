package com.github.vvinogra.iconfinderandroid.di.modules

import com.github.vvinogra.iconfinderandroid.di.annotations.PerFragment
import com.github.vvinogra.iconfinderandroid.ui.filters.view.FiltersFragment
import com.github.vvinogra.iconfinderandroid.ui.searchicons.view.SearchIconsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideSearchIconsFragment(): SearchIconsFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun provideFiltersFragment(): FiltersFragment
}