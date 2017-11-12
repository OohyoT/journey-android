package com.journey.app.api;

import com.journey.app.model.AuthInfo;
import com.journey.app.model.FollowUserRequestBody;
import com.journey.app.model.Fragment;
import com.journey.app.model.LoginResult;
import com.journey.app.model.Result;
import com.journey.app.model.Travel;
import com.journey.app.model.UpdatePasswordRequestBody;
import com.journey.app.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JourneyApi {

    // User APIs

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

    // Article APIs

    @GET("fragment?_sort=time&_order=desc")
    Call<ArrayList<Fragment>> getHomeFragments();

    @GET("travel?_sort=time&_order=desc")
    Call<ArrayList<Travel>> getHomeTravels();

    @GET("fragment/{id}")
    Call<Fragment> getFragment(@Path("id") int id);

    @GET("fragment")
    Call<ArrayList<Fragment>> getUserFragments(@Query("userId") int userId);

    @GET("travel/{id}?_embed=fragment")
    Call<Travel> getTravel(@Path("id") int id);

    @POST("fragment")
    Call<Fragment> addFragment(@Body Fragment fragment);

    @GET("fragment?_sort=id&_order=desc&_limit=1")
    Call<ArrayList<Fragment>> getLatestFragment();

}
