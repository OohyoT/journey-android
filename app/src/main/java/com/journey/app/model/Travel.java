package com.journey.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Travel {

    public int id;
    public int userId;
    public String title;
    public String location;
    public String time;
    public int likeCount;
    @SerializedName("fragment")
    public List<Fragment> fragments = new ArrayList<>();
    @SerializedName("image")
    public int imageId;

//    public static class Content {
//        public int id;
//        public String content;
//        public String createTime;
//        public String location;
//        public List<Integer> pictureIds = new ArrayList<>();
//    }
//
//    public static class Info {
//
//        public static class Comment {
//            public int id;
//            @SerializedName("travelsId")
//            public int travelId;
//            public int fragmentId;
//            @SerializedName("commentContent")
//            public String content;
//            public String createTime;
//            public String type;
//            @SerializedName("user")
//            public User author;
//        }
//
//        public static class Like {
//            public int id;
//            @SerializedName("travelsId")
//            public int travelId;
//            public int fragmentId;
//            public String createTime;
//            public String type;
//            @SerializedName("user")
//            public User author;
//        }
//
//        public int id;
//        public User author;
//        public String brief;
//        @SerializedName("commentNumber")
//        public int commentCount;
//        public Comment comment;
//        public int communityId;
//        public String createTime;
//        public int pictureId;
//        public String title;
//        @SerializedName("zanNumber")
//        public int likeCount;
//        @SerializedName("zans")
//        public List<Like> likes = new ArrayList<>();
//    }
//
//    @SerializedName("travelsContent")
//    public List<Content> contents = new ArrayList<>();
//    @SerializedName("travelsInfo")
//    public Info info;

}
