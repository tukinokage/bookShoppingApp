package com.example.bookshoppingapp.util;

import android.app.Activity;

public class UserInfoUtil {
    public static String getUserId(Activity activity){
       String userId = SharedPrefencesUtil
                .getSharedPrefences(activity)
                .sharePrefenceGetInfo(SharedPrefencesUtil.filename.USER_NAME_PASSWORD,
                        "Uid");

      // String userId = "1";

        return userId;
    }

    public static String getUserName(Activity activity){
        String userName = SharedPrefencesUtil
                .getSharedPrefences(activity)
                .sharePrefenceGetInfo(SharedPrefencesUtil.filename.USER_NAME_PASSWORD,
                        "Uname");

       // String userName = "小豆";

        return userName;
    }
}
