package com.journey.app.util;

import com.journey.app.BuildConfig;
import com.journey.app.Config;
import com.journey.app.api.AuthApi;
import com.journey.app.api.JourneyApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static JourneyApi api = null;
    private static AuthApi authApi = null;

    public static JourneyApi getApiInstance() {
        if (api == null) {
            synchronized (Api.class) {
                if (api == null) {
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
                    api = retrofit.create(JourneyApi.class);
                }
            }
        }
        return api;
    }

    public static AuthApi getAuthApiInstance() {
        if (authApi == null) {
            synchronized (Api.class) {
                if (authApi == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();

                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        OkHttpClient client = new OkHttpClient.Builder()
                                .addInterceptor(interceptor)
                                .build();
                        builder.client(client);
                    }

                    Retrofit retrofit = builder.baseUrl(Config.AUTH_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    authApi = retrofit.create(AuthApi.class);
                }
            }
        }
        return authApi;
    }

}
