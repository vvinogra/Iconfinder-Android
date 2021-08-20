package com.github.vvinogra.iconfinderandroid.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.github.vvinogra.iconfinderandroid.di.annotations.AppContext;

import java.text.NumberFormat;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    @AppContext
    Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    Resources provideResources(@AppContext Context context) {
        return context.getResources();
    }

    @Singleton
    @Provides
    NumberFormat provideNumberFormat() {
        return NumberFormat.getInstance();
    }
}
