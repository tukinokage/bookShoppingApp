package com.example.bookshoppingapp.ui.activity;

import android.content.Intent;

/**编写可调用的activity方法**/
public interface RegisterActivityBase {
    /**@param dialogText 提示文本**/
    void showToast(String dialogText);
    void turnActivity(Intent intent);
}
