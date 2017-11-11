package com.journey.app.util;

import com.journey.app.R;

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

}
