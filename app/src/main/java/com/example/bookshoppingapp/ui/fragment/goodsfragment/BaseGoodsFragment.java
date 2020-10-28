package com.example.bookshoppingapp.ui.fragment.goodsfragment;

import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.ui.fragment.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;

public interface BaseGoodsFragment  {
    /** *****此处写ui方法*
     ** 在对应fragment实现****** **/


    void showToast(String dialogText);


    void updateBookListView(List<BookItemInfo> booksList);

}
