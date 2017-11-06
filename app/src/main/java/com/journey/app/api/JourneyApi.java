package com.journey.app.api;

import com.journey.app.BuildConfig;
import com.journey.app.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JourneyApi {

    private static JourneyService service = null;

    public static JourneyService getService() {
        if (service == null) {
            synchronized (JourneyApi.class) {
                if (service == null) {
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
                    service = retrofit.create(JourneyService.class);
                }
            }
        }
        return service;
    }

}
