package com.journey.app.api;

import com.journey.app.model.AuthInfo;
import com.journey.app.model.FollowUserRequestBody;
import com.journey.app.model.LoginResult;
import com.journey.app.model.Result;
import com.journey.app.model.UpdatePasswordRequestBody;
import com.journey.app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthApi {

    @POST("user/regist")
    Call<Result> register(@Body AuthInfo authInfo);

    @POST("user/login")
    Call<LoginResult> login(@Body AuthInfo authInfo);

    @POST("user/password")
    Call<Result> updatePassword(@Body UpdatePasswordRequestBody body);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);

    @POST("user")
    Call<Result> updateUserInfo(@Body User user);

    @POST("user/follow")
    Call<Result> followUser(@Body FollowUserRequestBody body);

    @DELETE("user/deleteFollow")
    Call<Result> unfollowUser(@Body FollowUserRequestBody body);

    @GET("user/friends?loginUserId={loggedInUserId}")
    Call<Result> getFollowingUsers(@Path("loggedInUserId") int loggedInUserId);

}
