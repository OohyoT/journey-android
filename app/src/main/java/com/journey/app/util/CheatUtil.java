package com.journey.app.util;

import com.journey.app.R;
import com.journey.app.model.User;

public class CheatUtil {

    public static int getImageResId(int imageId) {
        switch (imageId) {
            case 1:
                return R.drawable.image1;
            case 2:
                return R.drawable.image2;
            case 3:
                return R.drawable.image3;
            case 4:
                return R.drawable.image4;
            case 5:
                return R.drawable.image5;
            case 6:
                return R.drawable.image6;
            case 7:
                return R.drawable.image7;
            case 8:
                return R.drawable.travel1;
            default:
                return 0;
        }
    }

    public static int getAvatarResId(int avatarId) {
        switch (avatarId) {
            case 1:
                return R.drawable.avatar1;
            case 2:
                return R.drawable.avatar2;
            case 3:
                return R.drawable.avatar3;
            default:
                return 0;
        }
    }

    public static User getUser(int id) {
        User user = new User();
        switch (id) {
            case 1:
                user.id = 1;
                user.username = "游走猫vivi";
                user.location = "北京";
                user.gender = "女";
                user.pictureId = 1;
                break;
            case 2:
                user.id = 2;
                user.username = "siedy";
                user.location = "成都";
                user.gender = "男";
                user.pictureId = 2;
                break;
            case 3:
                user.id = 3;
                user.username = "陈甜";
                user.location = "北京";
                user.gender = "女";
                user.pictureId = 3;
                break;
        }
        return user;
    }

}
