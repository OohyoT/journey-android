package com.journey.app.model;

import com.google.gson.annotations.SerializedName;

public class Fragment {

    public int id;
    public int userId;
    public String content;
    public String location;
    public int likeCount;
    public String time;
    @SerializedName("image")
    public int imageId;
    public int travelId;

}
