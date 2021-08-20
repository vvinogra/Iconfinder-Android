package com.github.vvinogra.iconfinderandroid.di;

import android.app.Application;

import com.github.vvinogra.iconfinderandroid.AppDelegate;
import com.github.vvinogra.iconfinderandroid.di.modules.ActivityModule;
import com.github.vvinogra.iconfinderandroid.di.modules.AppModule;
import com.github.vvinogra.iconfinderandroid.di.modules.FragmentModule;
import com.github.vvinogra.iconfinderandroid.di.modules.ImageModule;
import com.github.vvinogra.iconfinderandroid.di.modules.NetworkModule;
import com.github.vvinogra.iconfinderandroid.di.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        NetworkModule.class,
        ImageModule.class,
        AppModule.class,
        ActivityModule.class,
        FragmentModule.class,
        ViewModelModule.class
})
public interface AppComponent {
    void inject(AppDelegate appDelegate);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
