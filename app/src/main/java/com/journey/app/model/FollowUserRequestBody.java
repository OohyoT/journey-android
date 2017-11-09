package com.journey.app.model;

import com.google.gson.annotations.SerializedName;
import com.journey.app.util.Auth;

public class FollowUserRequestBody {

    public FollowUserRequestBody(int userId) {
        loggedInUserId = Auth.getLoggedInUserId();
        this.userId = userId;
    }

    @SerializedName("loginUserId")
    public int loggedInUserId;
    @SerializedName("id")
    public int userId;

}
