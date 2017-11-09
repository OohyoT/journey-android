package com.journey.app.model;

import com.google.gson.annotations.SerializedName;

public class User {

    public int id;
    public String username;
    public String location;
    public int pictureId;
    @SerializedName("sex")
    public String gender;

}
