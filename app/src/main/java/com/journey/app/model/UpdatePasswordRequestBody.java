package com.journey.app.model;

import com.google.gson.annotations.SerializedName;
import com.journey.app.util.Auth;

public class UpdatePasswordRequestBody {

    public UpdatePasswordRequestBody(String password) {
        loggedInUserId = Auth.getLoggedInUserId();
        this.password = password;
    }

    @SerializedName("loginUserId")
    public int loggedInUserId;
    public String password;

}
