package com.example.bookshoppingapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.dao.SPData_UserInfo;
import com.example.bookshoppingapp.presenter.LoginActivityPresenter;
import com.example.bookshoppingapp.presenter.LoginActivityPresenterlmpl;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;
import com.example.bookshoppingapp.util.ToastTool;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;

import java.util.Map;


/***登录页**/
public class LoginActivity extends BaseActivity implements LoginActivityBase {

    /*此处声明变量或初始化组件*/

    @BindView(R.id.login_activity_register_button)
    Button button_register;
    @BindView(R.id.login_activity_login_button)
    Button button_login;

    @BindView(R.id.login_activity_login_usrName_editText)
    EditText editText_name;
    @BindView(R.id.login_activity_login_password_editText)
    EditText editText_password;

    LoginActivityPresenter presenter = null;


    /////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Map<String,String> userInfo = SPData_UserInfo.getUserInfo_map(this);

        if( userInfo != null ){
            editText_name.setText(userInfo.get("name"));
            editText_password.setText(userInfo.get("password"));

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new LoginActivityPresenterlmpl(this);
    }



    @Override
    void initListener() {

        /*此处注册监听器*/
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText_name.getText().toString();
                String password = editText_password.getText().toString();
                presenter.login(name, password);

            }
        });


        /*跳转注册页面*/
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    public void showToast(String dialogText) {

        ToastTool.showToastOnMainThread(dialogText, this);

    }


}