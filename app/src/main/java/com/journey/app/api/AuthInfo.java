package com.journey.app.api;

import com.google.gson.annotations.SerializedName;

public class AuthInfo {

    public AuthInfo(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    @SerializedName("telNumber")
    public String phone;
    public String password;

}
