package com.kencorp.gads2020leaderboard.WebServices;

import android.os.Build;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


    private static final String URL="https://gadsapi.herokuapp.com/";

    // start interceptor
    //create logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // create okHttp client
    // private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);

    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            .readTimeout(45, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(new Interceptor() {

                // create Http headers
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {

                    Request request = chain.request();
                    request.newBuilder()
                            .addHeader("x-device-type", Build.DEVICE)
                            .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                            .build();

                    return chain.proceed(request);
                }
            })
            .addInterceptor(logger);

    // end interceptor
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())

            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S builderService(Class<S> serviceType){

        return retrofit.create(serviceType);
    }


}
