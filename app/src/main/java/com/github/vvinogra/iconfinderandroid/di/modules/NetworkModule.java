package com.github.vvinogra.iconfinderandroid.di.modules;

import com.github.vvinogra.iconfinderandroid.data.network.retrofit.IconFinderApi;
import com.google.gson.Gson;

import com.github.vvinogra.iconfinderandroid.BuildConfig;
import com.github.vvinogra.iconfinderandroid.data.network.retrofit.ApiKeyInterceptor;
import com.github.vvinogra.iconfinderandroid.data.network.retrofit.IconFinderApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new ApiKeyInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    IconFinderApi provideIconFinderApi(Retrofit retrofit) {
        return retrofit.create(IconFinderApi.class);
    }
}
