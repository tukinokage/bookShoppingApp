package com.example.bookshoppingapp.presenter;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.bookshoppingapp.dao.DataBaseHelper;
import com.example.bookshoppingapp.dao.SPData_UserInfo;
import com.example.bookshoppingapp.dao.UseDB_register;
import com.example.bookshoppingapp.model.entity.UserInfo;
import com.example.bookshoppingapp.ui.activity.LoginActivity;
import com.example.bookshoppingapp.ui.activity.RegisterActivity;


public class RegisterActivityPresenterlmpl implements RegisterActivityPresenter {

    RegisterActivity activity;
    public RegisterActivityPresenterlmpl(RegisterActivity activity){
        this.activity = activity;
    }

    @Override
    public void register(String name, String password,String re_password) {
        
        UseDB_register useDB_register = new UseDB_register(activity);

        String tip = "a";

        if(TextUtils.isEmpty(name)){

            tip = "用户名不能为空，请输入正确的用户名";
            activity.showToast(tip);

        }else if(TextUtils.isEmpty(password)){

            tip = "密码不能为空，请输入正确的密码";
            activity.showToast(tip);

        }else if ((TextUtils.isEmpty(re_password)) || (! password.equals(re_password)) ){

            tip = "前后两次密码不匹配，请重新输入";
            activity.showToast(tip);

        }else {

            DataBaseHelper helper =  new DataBaseHelper(activity);
            SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.query(DataBaseHelper.USER_INFO_TABLE,null,"userName=? and userPassword=?",new String[]{name,password},null,null,null);

            if(cursor!= null && cursor.getCount()>=1){

                tip = "该账号已存在，请重新注册";
                activity.showToast(tip);
                cursor.close();

            }else{

                int id = useDB_register.getuserAllNum(activity) + 1;
                
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(String.valueOf(id));
                userInfo.setUserName(name);
                userInfo.setUserPassword(password);
                useDB_register.register_SQL(userInfo);

                tip = "注册成功";
                activity.showToast(tip);
                Intent intent = new Intent(activity, LoginActivity.class);
                SPData_UserInfo.saveUserInfo(activity, userInfo.getUserName(), userInfo.getUserPassword(), userInfo.getUserId());
                activity.turnActivity(intent);

            }

        }


    }
}
