package com.example.bookshoppingapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.presenter.LoginActivityPresenter;
import com.example.bookshoppingapp.presenter.LoginActivityPresenterlmpl;
import com.example.bookshoppingapp.presenter.RegisterActivityPresenter;
import com.example.bookshoppingapp.presenter.RegisterActivityPresenterlmpl;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;
import com.example.bookshoppingapp.util.ToastTool;

import android.content.Intent;

import butterknife.BindView;
import butterknife.ButterKnife;



/***注册页面*******/
public class RegisterActivity extends BaseActivity implements RegisterActivityBase{

    /*此处声明变量或初始化组件*/

    @BindView(R.id.register_activity_usrName_editText)
    EditText register_editText_name;
    @BindView(R.id.register_activity_password_editText)
    EditText register_editText_passwprd;
    @BindView(R.id.register_activity_re_password_editText)
    EditText register_editText_re_password;

    @BindView(R.id.login_activity_register_cancel_button)
    Button register_button_cancel;
    @BindView(R.id.login_activity_register_submit_button)
    Button register_button_register;

    RegisterActivityPresenter presenter_register;



    /////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ActivityCollectorUtil.addActivity(this);
    }

    @Override
    public void showToast(String dialogText) {

        ToastTool.showToastOnMainThread(dialogText,this);

    }

    @Override
    public void turnActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter_register = new RegisterActivityPresenterlmpl(this);
    }

    @Override
    void initListener() {
        /*此处注册监听器*/
        /*返回登录页面*/
        register_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });
        /*实现注册*/
        register_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = register_editText_name.getText().toString();
                String password = register_editText_passwprd.getText().toString();
                String re_password = register_editText_re_password.getText().toString();
                presenter_register.register(name,password,re_password);

            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
