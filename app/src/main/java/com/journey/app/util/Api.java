package com.journey.app.util;

import com.journey.app.BuildConfig;
import com.journey.app.Config;
import com.journey.app.api.JourneyApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static JourneyApi instance = null;

    public static JourneyApi getApiInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();

                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        OkHttpClient client = new OkHttpClient.Builder()
                                .addInterceptor(interceptor)
                                .build();
                        builder.client(client);
                    }

                    Retrofit retrofit = builder.baseUrl(Config.API_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    instance = retrofit.create(JourneyApi.class);
                }
            }
        }
        return instance;
    }

}
