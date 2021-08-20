package com.github.vvinogra.iconfinderandroid.data.network.retrofit;

import com.github.vvinogra.iconfinderandroid.BuildConfig;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + BuildConfig.API_KEY);

        return chain.proceed(builder.build());
    }
}
