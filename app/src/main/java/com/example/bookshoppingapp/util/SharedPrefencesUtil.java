package com.example.bookshoppingapp.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/*******使用此工具类调用SharedPrefences*********/
public class SharedPrefencesUtil {

        /*****sp文件名*****/
        public static final class filename{
            //public static final String USER_NAME_PASSWORD = "USER_NAME_PASSWORD";
            public static final String USER_NAME_PASSWORD = "Userinfo";
        }


        private Activity activity;
        private static SharedPrefencesUtil instance;
        private SharedPreferences sharedPreferences;

        public SharedPrefencesUtil(Activity activity) {
            this.activity = activity;

        }

        public static SharedPrefencesUtil getSharedPrefences(Activity activity){
            if (instance == null){
                synchronized (SharedPrefencesUtil.class){
                    if (instance == null){
                        instance = new SharedPrefencesUtil(activity);
                    }
                }

            }
            return instance;
        }


        /**保存字符段
         * or
         * 修改字符段
         *
         * @Param SpName SharedPreferences文件名
         * @Param SpName saveKey 对应键名
         * @Param SpName saveValue 对应值
         * ****/
        public void sharedPrefenceSaveInfo(String SpName, String saveKey, String saveValue) {
            try {
                sharedPreferences = activity.getSharedPreferences(SpName, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(saveKey, saveValue);
                editor.commit();
            } catch (Exception e) {
                Log.e("SharedPreferences", "保存文件："
                        + SpName + " "
                        + saveKey + " "
                        + saveValue +
                        " 出错");

                e.printStackTrace();
            }

        }

    /**获取sharePrefence字符段的值
     *
     * @Param SpName SharedPreferences文件名
     * @Param SpName saveKey 对应键名
     * ****/
        public String sharePrefenceGetInfo(String SpName, String keyName){
            try{
                sharedPreferences = activity.getSharedPreferences(SpName, MODE_PRIVATE);
                Log.d("ceshi", sharedPreferences.getString(keyName, null));
                return sharedPreferences.getString(keyName, null);
             } catch (Exception e) {
                Log.e("sharePrefenceGet:", "出错");
                e.printStackTrace();
                return null;
            }
    }


    /**保存set类型字符段
     * or
     * 修改set类型字符段
     *
     * @Param SpName SharedPreferences文件名
     * @Param SpName saveKey 对应键名
     * @Param SpName saveValues 对应set集合
     * ****/
    public void sharedPrefenceSaveSet(String SpName, String saveKey, Set<String> saveValues) {
        try {
            sharedPreferences = activity.getSharedPreferences(SpName, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet(saveKey, saveValues);
            editor.commit();
        } catch (Exception e) {
            Log.e("SharedPreferences", "保存文件："
                    + SpName + " "
                    + saveKey + " "
                    + saveValues +
                    " 出错");

            e.printStackTrace();

        }

    }


    /**获取sharePrefence的set类型值
     *
     * @Param SpName SharedPreferences文件名
     * @Param SpName saveKey 对应键名
     * ****/
    public HashSet<String> sharePrefenceGetSet(String SpName, String keyName){
        try {
            sharedPreferences = activity.getSharedPreferences(SpName, MODE_PRIVATE);
            return (HashSet<String>) sharedPreferences.getStringSet(keyName, Collections.singleton("1"));
        } catch (Exception e) {
            Log.e("sharePrefenceGet:", "出错");
            e.printStackTrace();
            return null;
        }
    }

}
