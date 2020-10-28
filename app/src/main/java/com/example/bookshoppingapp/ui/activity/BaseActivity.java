package com.example.bookshoppingapp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshoppingapp.util.ActivityCollectorUtil;
import com.example.bookshoppingapp.util.premissionUtil;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        initListener();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        premissionUtil.verifyStoragePermissions(this);

        Log.e("activity", "++1");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**注册监听器**/
    abstract void initListener();
}
