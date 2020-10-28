package com.example.bookshoppingapp.dao;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.bookshoppingapp.presenter.LoginActivityPresenterlmpl;

import java.util.HashMap;
import java.util.Map;

public class SPData_UserInfo {

        public static final String MFILENAME = "Userinfo";

        public static boolean saveUserInfo(Context context, String name, String password, String uid) {

            boolean flag = false;
            SharedPreferences sp = context.getSharedPreferences(MFILENAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Uid", uid);
            editor.putString("Uname", name);
            editor.putString("Upassword", password);
            editor.commit();
            flag = true;
            return flag;

        }

        public static Map<String,String> getUserInfo_map(Context context){
            SharedPreferences sp = context.getSharedPreferences(MFILENAME, Context.MODE_PRIVATE);
            String Uname = sp.getString("Uname", null);
            String Upassword = sp.getString("Upassword", null);
            String Uid = sp.getString("Uid", null);
            Map<String,String> userMap = new HashMap<String, String>();
            userMap.put("name",Uname);
            userMap.put("password",Upassword);
            userMap.put("id",Uid);
            return userMap;
        }

}


