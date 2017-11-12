package com.journey.app.model;

import com.google.gson.annotations.SerializedName;

public class User {

    public int id;
    public String username;
    public String phone;
    public String location;
    @SerializedName("avatar")
    public int pictureId;
    public String gender;

}
