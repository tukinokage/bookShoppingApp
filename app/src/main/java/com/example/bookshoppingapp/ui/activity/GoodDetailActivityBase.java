package com.example.bookshoppingapp.ui.activity;

/**编写可调用的activity方法**/
public interface GoodDetailActivityBase {
    /**@param dialogText 提示文本**/
    void showToast(String dialogText);
    void showWisdomHUDSucceed(String dialogText);
    void showWisdomHUDError(String dialogText);
}
