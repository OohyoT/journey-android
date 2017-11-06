package com.journey.app.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JourneyService {

    @POST("user/regist")
    Call<Result> register(@Body AuthInfo authInfo);

    @POST("user/login")
    Call<Result> login(@Body AuthInfo authInfo);

}
