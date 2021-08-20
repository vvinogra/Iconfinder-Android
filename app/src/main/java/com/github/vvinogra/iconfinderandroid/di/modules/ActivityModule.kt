package com.github.vvinogra.iconfinderandroid.di.modules

import com.github.vvinogra.iconfinderandroid.di.annotations.PerActivity
import com.github.vvinogra.iconfinderandroid.ui.navigation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}