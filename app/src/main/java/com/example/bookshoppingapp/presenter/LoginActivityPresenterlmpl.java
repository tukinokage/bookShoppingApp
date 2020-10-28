package com.example.bookshoppingapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;


import com.example.bookshoppingapp.dao.SPData_UserInfo;
import com.example.bookshoppingapp.dao.UseDB_register;
import com.example.bookshoppingapp.model.entity.UserInfo;
import com.example.bookshoppingapp.ui.activity.LoginActivityBase;
import com.example.bookshoppingapp.ui.activity.MainActivity;
import com.example.bookshoppingapp.ui.activity.RegisterActivity;

import java.util.Map;

public class LoginActivityPresenterlmpl implements LoginActivityPresenter{

    LoginActivityBase activity;

    public  LoginActivityPresenterlmpl(LoginActivityBase activity){

        this.activity = activity;
    }


    @Override
    public void login(String name,String password) {

        if(TextUtils.isEmpty(name))
        {

            String tip = "用户名不允许为空，请输入正确的用户名";

            activity.showToast(tip);

        }
        else if (TextUtils.isEmpty(password))
        {

            String tip = "密码不能为空，请输入正确的密码";

            activity.showToast(tip);

        }else{

            UseDB_register useDB_register = new UseDB_register((Context)activity);
            UserInfo user = useDB_register.login_SQL(name, password);



            if(user != null){

                String tip = "登录成功";
                activity.showToast(tip);

                boolean flag_sp = SPData_UserInfo.saveUserInfo((Context)activity, name,password, user.getUserId());

                Intent i = new Intent((Context)activity,MainActivity.class);
                ((Context) activity).startActivity(i);


            }else{

                String tip = "用户名不正确或密码不正确，请重新输入";
                activity.showToast(tip);

            }

            }

        }
        /*

       数据库访问、数据逻辑

        */
    }

