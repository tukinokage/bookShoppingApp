package com.example.bookshoppingapp.ui.fragment.mydetailfragment;

import android.content.Intent;

public interface BaseMyDetailFragment {
    /** *****此处写ui方法*
     ** 在对应fragment实现****** **/

    void showToast(String dialogText);
    void turnActivity(Intent intent);


}
